# How Git Works

# Git Layers
Like a onion that has different layers, git has 4 layers, from the deep down, git is:

## 1- A Persistent Map
This map is just keys and values, values can be any sort of content in files, and keys are the SHA1 hashes that git provides.

Every object in git has its own SHA1.

SHA1 are unique in the whole universe, as it's really really unlikely (maybe impossible) that two different objects hash to the same SHA1, so data is guaranteed not to be lost, and there are no collisions.

Git persists the contents of a file in the repository as a blob, with the name of the compressed file bein the SHA1 key, and stores directories as a tree.

## 2- A Stupid Content Tracker
### What is a Commit?
A commit in git is just a text file that contains the keys of the files and directories (blobs and trees) committed that are store inside the .git directory, and also contains the author and committer information.

and the commit file itself gets stored in the .git repository, and gets compressed and also gets its own SHA1 ! just like any other file.

If we create two commits and peak inside the commit file of the last commit, we'll see that it has also a parent SHA1, which is the SHA1 of the commit before it in the commit tree.

In the new commit, git creates new blobs only for the files that got changed, but for the files that have not got changed since the previous commit, git will use those same blobs in the new commit as well, which is why git repositories don't take that much space, even when the number of commits and versions is big.

Git also does another optimization for large files and repositories, which is that it may only store the differences between the files instead of creating a whole new big file for a small change.

## 3- A Revision Control System
### What is a Branch?
A branch is just a SHA1 key stored in a file named with the branch name inside a folder inside .git called "refs", a key that is associated with a commit, this is it, a branch is a reference to a commit.

### What is HEAD?
HEAD is just a reference to a branch, it's a file that is stored inside the .git folder named "HEAD" and contains the path of the branch we're currently working on in the working directory.

### What happens when we commit?
When you commit in a branch, HEAD and the branch reference the newly created commit, but any other branch still doesn't references, which creates the tree structure that we know.

### What happens when we switch branch?
When we switch branch using switch or checkout, HEAD references this branch and git moves the content of the commit that the branch references to the working directory, in efficient way.

### Merging
When we merge a branch into another, what happens is that git creates a new commit that has two parents instead of one, and all the changes of both branches get applied in the working directory, unless there is a conflict.

A conflict happens when two branches have commits that edited the same portion of a file, so git won't be able to automatically merge them, we have to solve the conflict ourselves, and then continue the merge.

### Fast-Forward
When we merge a branch into another, and then switch to the other branch, what happens if we also merge the first branch to it also?

The expected behavior is that git will create a new commit that has two parents, the first branch latest commit (the first merge commit), and the latest commit of the other branch.

But what happens is an operation called "Fast-Forward", git makes the branch reference the merge commit the was created in the first branch, saving space and also simplifying the commit tree.

### Rebasing
When we rebase a branch on top of another, what happens is that instead of creating a new commit that has two parents like merge, git first detaches the branch starting from the commit where they're different, and then puts this detached part on top of the other branch's commits, and moving the branch reference to that latest commit.

This is in theory, but actually git can't do this, it can't change the database objects (commits and files) stored in the repository.

What really happens is that git creates new commits that are identical to the current commits, and places them on top of the latest commit of the other branch, this is why the SHA1 of the commits change, because while they have the same contents, their parents changed, so their SHA1 is also changed.

## 4- A Distributed Revision Control System
Clones of repositories in git are technically peers, they all have the same importance.

While in real projects, what we do is assign a certain repo as the main repo that the other local repos sync with and make it the master repo, this is not something that git itself does, it's a convention and an agreement, all peers in git are equal.

### Remote branches are not different
Remote branches are not different than local branches, they are references to commits.

Whenever we synchronize a remote branch with a local branch, they will both reference the same commit, which is what happens automatically when we clone a GitHub repo, a local branch is created called main, which is synchronized with the remote origin branch also called main.

The commands that ensure this synchronization are the usual git push and git pull commands.

Git pull is composed of two commands, git fetch, which updates the remote branch on the local repo, and git merge, which merges this branch with the current branch on the local repo.

Note: It's almost always a bad idea to force push to a remote repo in the case of conflicts, especially in a team project, because that will mess up the other developer's local repos as well when they try to pull the changes.




<hr>

[Mahmoud Abdelazim](https://github.com/MahmoudAbdelazim)