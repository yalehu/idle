package com.away1994.idlerest.jobs;

import com.away1994.common.utils.DirectoryUtils;
import com.away1994.idlerest.message.JobProgressMessage;
import com.away1994.lang.parser.LanguageParser;
import com.away1994.lang.parser.Parser;
import com.away1994.lang.parser.impl.java.JavaLanguageParser;
import com.away1994.lang.parser.impl.objectivec.ObjectiveCLanguageParser;
import com.away1994.lang.project.Project;
import com.away1994.lang.symbols.impl.PathImpl;
import com.away1994.utils.PathUtils;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.File;

public class ParseJob implements DetailedJob {
    private SimpMessagingTemplate template;

    private Project project;

    private String state = "NEW";

    private String taskName = "";

    private Integer progess = 0;

    public ParseJob(SimpMessagingTemplate template, Project project) {
        this.template = template;
        this.project = project;
        this.project.setParsing(true);
    }

    @Override
    public int getProgress() {
        return 0;
    }

    @Override
    public String getState() {
        return null;
    }

    @Override
    public String getJobName() {
        return "parse";
    }

    @Override
    public void run() {
        state = "RUNNING";
        sendProgress();

        System.out.println("do parse job");
        DirectoryUtils.deleteDir(new File(project.getSymbolsPath()));
        File client = new File(project.getSymbolsPath());
        client.mkdirs();
        LanguageParser languageParser = new ObjectiveCLanguageParser();
        if (project.getLanguage().equals("java8")) {
            languageParser = new JavaLanguageParser();
        }
        Parser parser = new Parser(new PathImpl(project.getProjectPath()), languageParser);
        parser.setOutputPath(project.getSymbolsPath());
        parser.runParseStateMachine();
        project.setParsed(true);
        project.setParsing(false);

        progess = 100;
        state = "DONE";
        sendProgress();

        System.out.println("finish parse job");
    }


    public void sendProgress() {
        JobProgressMessage temp = new JobProgressMessage(this.getJobName());
        temp.setProgress(progess);
        temp.setState(state);
        temp.setTaskName(taskName);
        template.convertAndSend("/topic/status", temp);
    }

}
