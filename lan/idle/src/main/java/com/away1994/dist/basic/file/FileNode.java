package com.away1994.dist.basic.file;

import com.away1994.dist.basic.NodeVisitor;

public class FileNode extends FileDescriptionNode {


    public FileNode() {
    }

    public FileNode(String path) {
        super(path);
    }

    @Override
    public String dotString() {

        String path = this.getPath().replace('/', '_');
        return "\"" + path + "\" [label = \"" + this.getName() + "\"];";
    }


    //region base node

    @Override
    public <T> T accept(NodeVisitor<? extends T> visitor) {
        if (visitor instanceof FileVisitor) {
            return ((FileVisitor<? extends T>) visitor).visitFileNode(this);
        }
        return visitor.visitChildren(this);
    }

    //endregion
}
