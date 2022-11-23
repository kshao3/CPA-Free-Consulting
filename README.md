# Summary
This project is used as a platform for the financial & legal consulting purpose, on which upload & download documents 
and user forums will be permitted

# Team members:
- Kaijie Shao   email: kshao3@dons.usfca.edu
- Jiayi Gu      email: jgu3@mail.sfsu.edu
 
# Languages & Tools
Front-end：Vue 2.0 <br/>
Framework：Element-UI <br/>
Back-end：Java <br/>
Framework：SpringBoot 2.3.7、MyBatis-Plus、Redis <br/>
Database：Mysql 5.0 <br/>
Tools：IntellJ IDEA 2019,Webyog SQLyog,VS Code
Cloud：Alibaba Cloud OSS
    
# Further-reading
The system adopts the MVC three-tier architecture idea for development, and adopts the efficient front-end and back-end
separating techniques for deployment. The advantage of this method is when back-end cloud host is down, the front-end
cloud host can still provide page access. 

# Other techniques
Redis cache: when the user clicks the mailbox to send the verification code,it will cache the verification code in Redis,
and the cache expiration time is set to one minute.
Ikaptcha verification code: display a graphic verification code when logging in to prevent crawlers from hacking.
Alibaba Cloud OSS storage: when a user uploads an attachment, the file will be saved in OSS, and the file will be 
downloaded from OSS.
JWTToken: It is a token technology. When the user logs in successfully, a token  will be generated. When accessing other 
interfaces, the token will be used to access the interface.

# Example
Login Page

![one](https://user-images.githubusercontent.com/68679933/203466223-31ca3501-7889-4e18-8501-35b50e5db8f1.jpg)

Upload a document
![two](https://user-images.githubusercontent.com/68679933/203466261-f30ed373-8743-4c38-a1e3-c8e98bb86a8e.jpg)

# In the Future
In the later stage, I am planning to add corresponding payment functions, which can make the website have a source of 
income, and also add corresponding advertising modules, so it to attract investors to come and invest in advertising. 
Most likely,I will also consider to add a message module that allows users to leave messages and comments on each other.
