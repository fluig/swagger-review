package com.api;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.util.ArrayList;

@Mojo(name = "review", requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class MojoExecution extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(readonly = true)
    private ArrayList<String> ignoreRules = null;

    @Override
    public void execute() throws MojoExecutionException {

        System.out.println("IGNORE: " + ignoreRules);

        ArrayList<String> pathsToIgnore = new ArrayList<>();

        pathsToIgnore.add(Utils.SUFIX_FILE_NAME);

        Utils.executeValidation(project.getBasedir().toPath(), pathsToIgnore, ignoreRules);
    }

}
