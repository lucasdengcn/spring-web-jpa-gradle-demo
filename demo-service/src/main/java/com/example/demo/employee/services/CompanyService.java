package com.example.demo.employee.services;

import com.example.demo.employee.apis.input.CompanyCreateInput;
import com.example.demo.employee.entities.Company;
import com.example.demo.employee.exception.RecordNotFoundException;
import com.example.demo.employee.mapper.CompanyMapper;
import com.example.demo.employee.models.CompanyConnection;
import com.example.demo.employee.models.CompanyEdge;
import com.example.demo.employee.models.CompanyModel;
import com.example.demo.employee.repository.CompanyRepository;
import com.example.demo.models.CursorInputModel;
import com.example.demo.models.CursorPageModel;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public CompanyModel create(CompanyCreateInput companyCreateInput){
        Company company = companyMapper.inputToEntity(companyCreateInput);
        companyRepository.save(company);
        return companyMapper.entityToModel(company);
    }

    @Transactional
    public CompanyModel save(CompanyCreateInput companyCreateInput) throws Throwable {
        var company = companyRepository.findById(1).orElseThrow((Supplier<Throwable>) () -> new RecordNotFoundException("", "", "", ""));
        companyRepository.save(company);
        return companyMapper.entityToModel(company);
    }

    public Page<CompanyModel> findCompanies(@NonNull Integer pageSize, @NonNull Integer pageIndex){
        Pageable pageable = Pageable.ofSize(pageSize).withPage(pageIndex);
        Page<Company> companyPage = companyRepository.findAll(pageable);
        //
        //
        return companyPage.map(company -> companyMapper.entityToModel(company));
    }

    public CompanyModel findCompanyById(@NonNull Integer companyId){
        Optional<Company> companyOptional = companyRepository.findById(companyId);
        if (companyOptional.isPresent()){
            return companyMapper.entityToModel(companyOptional.get());
        }
        throw new RecordNotFoundException("404", "Company is not found: " + companyId, "Company", "traceId");
    }

    public CompanyConnection findCompaniesWithCursor(CursorInputModel cursorInputModel){
        //
        int pageSize = cursorInputModel.pageSize();
        //
        List<Company> companyList;
        boolean hasNextPage;
        boolean hasPreviousPage = false;
        String startCursor;
        String endCursor;
        // Sorting in DESC Order
        Integer cursorId = null;
        if (StringUtils.hasLength(cursorInputModel.getCursor())) {
            cursorId = CursorInputModel.decode(cursorInputModel.getCursor());
        }
        if (cursorInputModel.hasCursors() && cursorInputModel.hasNextPageCursor()) {
            companyList = companyRepository.findNextWithPageCursor(cursorId, pageSize + 1);
            endCursor = CursorInputModel.encode(companyList.getFirst().getId());
            int endCursorIndex = getEndCursorIndex(companyList.size(), pageSize);
            startCursor = CursorInputModel.encode(companyList.get(endCursorIndex).getId());
            //
            hasNextPage = companyList.size() > pageSize;
            List<Company> temp = companyRepository.findPreviousWithPageCursor(cursorId, 1);
            hasPreviousPage = !temp.isEmpty();
        } else if (cursorInputModel.hasCursors() && cursorInputModel.hasPrevPageCursor()) {
            companyList = companyRepository.findPreviousWithPageCursor(cursorId, pageSize + 1);
            startCursor = CursorInputModel.encode(companyList.getFirst().getId());
            int endCursorIndex = getEndCursorIndex(companyList.size(), pageSize);
            endCursor = CursorInputModel.encode(companyList.get(endCursorIndex).getId());
            //
            hasPreviousPage = companyList.size() > pageSize;
            List<Company> temp = companyRepository.findNextWithPageCursor(cursorId, 1);
            hasNextPage = !temp.isEmpty();
        } else {
            companyList = companyRepository.findWithCursor(pageSize + 1);
            endCursor = CursorInputModel.encode(companyList.getFirst().getId());
            int endCursorIndex = getEndCursorIndex(companyList.size(), pageSize);
            startCursor = CursorInputModel.encode(companyList.get(endCursorIndex).getId());
            hasNextPage = companyList.size() > pageSize;
        }
        if (companyList.isEmpty()){
            return new CompanyConnection(null, new CursorPageModel(null, null, false, false));
        }
        //
        List<CompanyEdge> companyEdges = companyList.stream().limit(cursorInputModel.pageSize())
                .map(company -> {
                    CompanyModel companyModel = companyMapper.entityToModel(company);
                    return new CompanyEdge(CursorInputModel.encode(company.getId()), companyModel);
                }).toList();
        //
        CompanyConnection companyConnection = new CompanyConnection(companyEdges, new CursorPageModel(startCursor, endCursor, hasNextPage, hasPreviousPage));
        log.info("CompanyConnection: {}", companyConnection);
        return companyConnection;
    }

    private static int getEndCursorIndex(int size, int pageSize) {
        return size > pageSize ? pageSize - 1 : size - 1;
    }
}
