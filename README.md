Jabox ALM
==========
Copyright &copy; 2009-, Dimitris Kapanidis. Licensed under [GPL License].

About
-----
Jabox ALM is an open source Application Lifecycle Management platform. It provides a full-blown Java Enterprise Development Factory using your favorite tools, in less than 5 minutes.

It's purpose is to manage a seemless integration between Software Configuration Management ([SCM]), Issue Tracking System ([ITS]), Continuous Integration System ([CIS]), Repository Management System ([RMS]), Code Quality Management ([CQM]) and other tools in order to provide a complete Software Factory.

Its core is implementation agnostic and there are plugins that glue together each module, in order to provide better extensibility.

It supports automatic download and installation of the modules so that the whole installation process is managed directly by Jabox.

There are no external dependencies apart from a JRE and two port numbers (one for Jabox, and one for the Container of your ALM)

Downloads
---------
Binaries such as WAR files can be found at the [Download page].

Source
------
The source of Jabox ALM can be found on [GitHub]. Fork us!

Website
-------
All about Jabox ALM can be found on our [website].
News can be found on our [blog].
Follow us on Twitter @[jaboxALM].
Sign-up to our [mailing list].

Getting Started
---------------

You can launch jabox directly from command line:

    java -jar jabox-webapp-*-jetty-console.war

or deploy the war to a Servlet Container such as Tomcat.

Supported Modules
=================

Below you will find a list of modules that Jabox currently supports. Each of these components is isolated meaning that adding new modules is easy to do.

Software Configuration Management ([SCM])
---------------------------------------

- Open Source:
 - [Subversion](http://subversion.tigris.org/)
 - [Git](http://git-scm.com/)
- Online Services:
 - [Github](https://github.com/)
 - [Beanstalk](http://beanstalkapp.com/)

Issue Tracking System ([ITS])
---------------------------

- Open Source:
 - [Redmine](http://www.redmine.org/)
 - [Bugzilla](http://www.bugzilla.org/)
 - [Mantis](http://www.mantisbt.org/)
 - [Jtrac](http://www.jtrac.info/)

Continuous Integration System ([CIS])
-------------------------------------

- Open Source:
 - [Jenkins](http://jenkins-ci.org/)

Repository Management System ([RMS])
------------------------------------

- Open Source:
 - [Artifactory](http://www.jfrog.com/products.php)
 - [Nexus](http://nexus.sonatype.org/)

Code Quality Management ([CQM])
-------------------------------

- Open Source: 
 - [Sonar](http://www.sonarsource.org/)

[GPL License]: https://github.com/jabox/jabox/raw/master/LICENSE.txt
[Download page]: http://redmine.jabox.org/projects/jabox/wiki/Download
[GitHub]: https://github.com/jabox/jabox
[website]: http://www.jabox.org/
[blog]: http://jabox.tumblr.com/
[mailing list]: http://groups.google.com/group/users-jabox/topics
[jaboxALM]: http://twitter.com/jaboxALM
[SCM]: http://redmine.jabox.org/projects/jabox/wiki/Software_Configuration_Management
[ITS]: http://redmine.jabox.org/projects/jabox/wiki/Issue_Tracking_System
[CIS]: http://redmine.jabox.org/projects/jabox/wiki/Continuous_Integration_System
[RMS]: http://redmine.jabox.org/projects/jabox/wiki/Repository_Management_System
[CQM]: http://redmine.jabox.org/projects/jabox/wiki/Code_Quality_Management
