# Commons [![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0) [![Build Status](https://travis-ci.org/coding-eval-platform/commons.svg?branch=master)](https://travis-ci.org/coding-eval-platform/commons) [![download](https://api.bintray.com/packages/coding-eval-platform/maven/commons/images/download.svg)](https://bintray.com/coding-eval-platform/maven/commons/_latestVersion)

Project containing several commons libraries to be reused across the platform.

**Java 11 is required for this library to be used**

## How to use it?

### Get it!

#### Bintray repository

##### Configure the repository

Add the following to your project's `pom.xml` file:

```xml
<repositories>
    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-coding-eval-platform-maven</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/coding-eval-platform/maven</url>
    </repository>
</repositories>
<pluginRepositories>
    <pluginRepository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-coding-eval-platform-maven</id>
        <name>bintray-plugins</name>
        <url>https://dl.bintray.com/coding-eval-platform/maven</url>
    </pluginRepository>
</pluginRepositories>
```

##### Use the libraries

Add the necessary dependency in your project's `pom.xml` file. For example:

```xml
<dependencies>
	<dependency>
		<groupId>ar.edu.itba.cep</groupId>
	   	<artifactId>commons-web-authentication</artifactId>
	    <version>${commons.version}</version>
	</dependency>
</dependencies>
```

#### Build from source

You can also build your own versions of the libraries. Maven is required for this.


```bash
$ git clone https://github.com/coding-eval-platform/commons.git
$ cd commons
$ mvn clean install -P local-deploy
```


## Development

### Prerequisites


1. Clone the repository or download source code:

	```
	$ git clone https://github.com/coding-eval-platform/users-service.git
	```
	or

	```
	$ wget https://github.com/coding-eval-platform/users-service/archive/master.zip
	```

2. Install Maven

	**Note: Maven 3.5+ is required!**
	
	#### Mac OS X
	
	```bash
	$ brew install maven
	```

	#### Ubuntu
	```bash
	$ sudo apt-get install maven
	```

	#### Other OSes
	Check [this site](https://maven.apache.org/install.html).
	

### Build

1. Change working directory to project root

	```bash
	$ cd <project-root>
	```


2. Build artifacts

	```bash
	$ mvn clean package
	```

## License

Copyright 2019 Bellini & Lobo

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

## Authors

- [Juan Marcos Bellini](https://github.com/juanmbellini)
- [Daniel Lobo](https://github.com/lobo)

