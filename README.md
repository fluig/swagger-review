To run it, include the profile written below to the pom at the pom.xml of the project that contains your endpoints (generally it's something like v1)

```
    <build>
        <plugins>
            <plugin>
                <groupId>com.fluig</groupId>
                <artifactId>swagger-review</artifactId>
                <version>1.0-SNAPSHOT</version>
                <executions>
                    <execution>
                        <phase>install</phase>
                        <goals>
                            <goal>review</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```

So then use the command: mvn clean install -Papi-review
