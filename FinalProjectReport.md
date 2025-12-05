# Final Project Report: CSPB Cohort Connect
**Class:** 3112 Professional Development in CS

**Author:** Hiatt Campbell

**Website:** https://main.dsmbr0s7i3azg.amplifyapp.com/ 

## I. Introduction
### Vision
My original goal with this project was to learn Java and apply that knowledge by building and deploying a full-stack web application that connects current and past students in the post-baccalaureate program. Along with learning a new language, I also wanted to become familiar with the popular Java framework, Spring Boot. My personal motivation for this project was to create a repository I could showcase to potential employers when I began my job search. My broader goal was to create a networking app for those who have been through or are currently part of the post-baccalaureate program to build connections, stay up to date on the program and the tech world, and find mentors/mentees.

### Goals
The original goals of my project were to:
- become sufficient at programming in Java, 
- build a front-end in React and utilize SQL to connect the front-end to the back-end,
- utilize AWS tools to deploy a full-stack web application,
- and network with other students and encourage them to use the platform.

To measure my progress toward my goals, I set deliverable expectations for myself. To showcase my aptitude for programming in Java, I would design and build a back-end API that can interact appropriately with an SQL database using Java and its frameworks. The full-stack app, built with React and SQL, would have an interactive user interface that allows users to perform CRUD operations on an SQL database. Using AWS tools for deployment would mean I could successfully create a continuous integration/deployment environment with CodeDeploy, an AWS deployment automation tool. Finally, networking with other students would mean that I would have several active student users in the last weeks of class.

## II. Background
The main aim of my project was to produce a full-stack application that enables current and former students of the CSPB program to interact with one another. This project was important for my professional development because I wanted a significant project to include on my resume to showcase my development skills. This project can be used to demonstrate to employers that I am capable of creating a back-end API, similar to the tasks expected of a back-end developer. In addition to the physical output, this project also expanded my programming language repertoire to include Java.  

## III. Methodology, Materials and Methods
I used a combination of tools to gain knowledge and skills throughout my project. The way I obtained knowledge throughout the semester differed depending on the tool/skill I wanted to learn. If I wished to learn something I was very unfamiliar with, such as Docker, I would begin by gathering resources and consuming them before jumping into working with the tool or utilizing the skill. I will refer to this as a 'passive learning strategy.' If I were more comfortable with the skill or did not feel I had a strong background, I would dive into development with the aforementioned skill and learn as I went, using an active learning strategy. 

My passive learning resources included watching YouTube videos, completing LinkedIn Learning courses, and reading introductions to tools, such as Medium articles. My active learning methods often involved using documentation for the tool to use immediately in development or utilizing a tutorial to walk me through how to perform a task/implementation. 

## IV. Results
### Learning achievements
Throughout this semester I learned a combination of hard skills and soft skills. The hard skills I learned included/the tools I worked with:
- The Java language, which I used to write my back-end API,
- Spring Boot, the framework in which I used run my back-end,
- Maven, the build tool I used to build my back-end,
- Docker and Docker compose, which I used for running my application and for building an image for my EC2 instance,
- Authorization and authentication using JWT and Spring Security, 
- EC2, which I used to deploy my app’s API,
- React, where I built my front-end application and fetched my data from my back-end,
- Amplify, which I used to deploy my app’s front-end.

In addition to learning how to use these tools, I also gained valuable soft skills, including project time management, scoping skills, and writing thorough updates.  

### Project Assessments 
While I reached my goals of learning Java and how to use Spring Boot by building a back-end API and front-end React app, I did not meet my deployment, and thus my networking deliverable goals. Currently, my project is successful in the development environment, but my API is not successful in the deployed version of my app due to mixed content errors, where my browser blocks the HTTP request sent by my API since my Amplify hosted website uses HTTPS for security. 


## V. Discussion / Reflection
I think I did a good job of reaching my first couple of goals, but fell short of meeting my deployment goal due to my vast underestimation of how long the deployment process would take to figure out. I had never used an EC2 instance before, nor had I used the aws scripting commands, so the learning curve for this part of my project was difficult, after only having used Heroku and Render for deployments before. In the future, I will overestimate the time it takes to learn how to use a new AWS tool. 


## VI. Conclusion
### Summary
I am happy with the point I am at in my project but do not consider it finished yet. I will continue to work on getting the deployed version of my project to work. Overall, this project challenged me to learn new tools in a new language. I was able to successfully build a functioning full-stack application in a new language and framework (Java + Spring Boot). I expanded my technical skillset across back-end, front-end, authentication, containerization, and cloud deployment tools. I learned the importance of realistic project scoping. Finally, I created a project that can continue to grow beyond the course and serve as a portfolio item.

### Next Steps
I am currently in the process of adding a load balancer via the EC2 load balancer service on AWS, which includes adding a certificate with my own domain name, which I have already purchased. Additionally, making the UI look nicer is something I will come back to once my API is actually working on my front-end deployed application. 


## VII. References or Bibliography
- https://www.linkedin.com/learning/spring-boot-and-react-build-scalable-and-dynamic-web-apps/modern-web-apps-with-spring
- https://spring.io/projects/spring-security
- https://cloudcaptain.sh/blog/spring-boot-ec2.html
- https://docs.spring.io/spring-boot/how-to/deployment/cloud.html 

