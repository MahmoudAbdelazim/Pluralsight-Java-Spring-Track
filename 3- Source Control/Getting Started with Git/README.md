# Getting Started with Git
Git is a version control system (VCS), which is a software that records changes made to files over time, giving us the ability to revert back to a previous file version or project version after making changes, and many other useful abilities.

Git allows us to version control any plain text files, not just source code.

## The Three States of a File
A file inside a git repo can be in either of these states: Committed, Modified, Staged or Untracked.

## The Three Main Parts of Git
1. The `.git` Directory (Repository): Where git stores the metadata and compressed database for the project's versions, files in this part have the state of "Committed"
2. The Working Directory: A checkout of one version of the project, pulled from the compressed database from the .git directory, files in this part have the state of "Modified" or "Untracked" if it's a completely new file.
3. The Staging Area (Index): used to build changes to commit to the repository, files in this part have the state of "Staged".

## Pushing files to a repository
In order to push a file or files to a git remote repository, these three basic commands are applied:
1. `git add .`: This adds the currently untracked or modified files to the staging area.
2. `git commit -m "commit message"`: This commits the changes currently in the staging area to the repository.
3. `git push -u origin main`: This pushes the commits in our local directory to the main branch of the origin remote directory.

<hr>

# Git Basic Commands

## git status
Tells us which branch we're on, and which files are untracked, modified, removed, or staged.

following by --short gives us a short status instead.

## git add
Running git add on an untracked or modified file, puts it in the staging area in order to be committed later. The file is then said to be "Tracked" and "Staged".

## git diff
Answers two questions, what changes have I staged that are ready to be committed? (using git diff --staged), and what changes have I made but not yet staged? (using git diff).

## git commit
Commits changes that are currently in the staging area as a snapshot in the repository.

## git push
Used to push our local commits to a remote repository branch.

## git pull
Used to pull the latest changes from a remote repository branch.

## git log
Used to git a log of commits made to a repository in a reverse chronological order, showing the hash of each commit, the date, the author and the commit message.

git log --oneline, gives us a short log for each commit in a single line.

git log -2, gives us the log of only last two commits.

git log --stat, gives us a log with addition of showing which files are changed and how many lines were added/deleted.

git log --patch, gives us the full diff of the exact changes in each changed file in each commit

<hr>

# Branching
Branching allows us to create multiple versions of a project, splitting the repository starting from a specific command.
Commits that are done on a branch won't be applied to the other branches, until a merge operation is applied.

## git checkout
Used to move to another branch, and can be followed with -b before the name of the branch to create a new branch and move to it.

## git branch
Tells us which branch we're currently in.

## git merge
Used to merge a branch into another, in other words, used to incorporate changes in a branch into another branch.
We call git merge branch_name while we're in the branch that we want to merge another branch into, not the other way around.

<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)