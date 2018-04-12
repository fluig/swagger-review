package com.api;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name = "review", requiresDependencyResolution = ResolutionScope.COMPILE_PLUS_RUNTIME)
public class MojoExecution extends AbstractMojo {

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Override
    public void execute() throws MojoExecutionException {

        try {
            List<Path> javaFiles = Files.find(
                    project.getBasedir().toPath(),
                    Integer.MAX_VALUE,
                    (path, basicFileAttributes) -> path.toFile().getName().endsWith(".json"),
                    FileVisitOption.FOLLOW_LINKS
            ).collect(Collectors.toList());

            for (Path path : javaFiles) {
                this.getLog().warn(path.toString());
                new SwaggerAnalyzer(path.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
