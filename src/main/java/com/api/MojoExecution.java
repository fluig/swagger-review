package com.api;

import com.api.rules.EnsureOperationIdRule;
import com.api.rules.SwaggerRule;
import com.api.rules.SwaggerRuleFailure;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
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

                // para cada regra
                // result.add( regra.execute())


                //
                // serializa o result
                this.getLog().warn(path.toString());

                SwaggerParser p = new SwaggerParser();
                Swagger swagger = p.read(path.toString());

                SwaggerRule rule = new EnsureOperationIdRule();
                final List<SwaggerRuleFailure> execute = rule.execute(swagger);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
