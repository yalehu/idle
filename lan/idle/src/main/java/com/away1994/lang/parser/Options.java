package com.away1994.lang.parser;

import java.util.ArrayList;

public class Options {
    private ArrayList<String> ignorePaths = new ArrayList<>();

    private ArrayList<String> ignoreFiles = new ArrayList<>();

    private Boolean verbose = false;

    public Options() {
    }

    public ArrayList<String> getIgnoreFiles() {
        return ignoreFiles;
    }

    public void setIgnoreFiles(ArrayList<String> ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }

    public ArrayList<String> getIgnorePaths() {
        return ignorePaths;
    }

    public void setIgnorePaths(ArrayList<String> ignorePaths) {
        this.ignorePaths = ignorePaths;
    }

    public Boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(Boolean verbose) {
        this.verbose = verbose;
    }
}
