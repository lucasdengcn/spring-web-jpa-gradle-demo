## Squash and rebase

Squashing and rebasing commits before asking for a review is indeed a common best practice in many software development workflows. It helps to keep the commit history clean, organized, and easier to understand for reviewers and future developers working on the codebase.

When you squash commits, you combine multiple commits into a single commit, which makes it easier to follow the logical changes made in the code. This is especially useful when you have made several small, incremental commits during your development process. Squashing allows you to present a more concise and cohesive set of changes for review.

Rebasing, on the other hand, allows you to incorporate the latest changes from the main branch into your feature branch. By rebasing your branch onto the latest commit of the main branch, you ensure that your changes are based on the most up-to-date code. This can help to minimize conflicts and ensure that your code integrates smoothly with the rest of the codebase.

To squash and rebase your commits, you can follow these general steps:

1. Ensure you have committed all your changes **locally**.
2. Checkout your **feature branch**.
3. Run the **rebase command** to incorporate the latest changes from the main branch: git rebase main.
4. During the rebase process, **resolve any conflicts** that may arise.
5. Once the rebase is complete, use the interactive rebase **(git rebase -i) to squash your commits**.
6. In the interactive rebase, **specify which commits you want to squash**, combine them, and provide a new commit message.
7. Save and exit the interactive rebase.
9. Push the updated branch: git push --force.


It's worth mentioning that squashing and rebasing should be done carefully, especially if you're working in a collaborative environment. It's important to communicate with your team and follow any established guidelines or conventions for rebasing and handling commit history.

Including a guideline in your contributors' guidelines to squash and rebase commits before asking for a review can help ensure that the code review process is more efficient and that the commit history stays clean and meaningful.