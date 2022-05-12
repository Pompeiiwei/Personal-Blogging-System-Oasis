<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateDetails</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.2.10/semantic.css"/>
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <script type="text/javascript" src="./js/userNameCompare.js"></script>
    <style type="text/css">
        body{
            background-image: url("./images/5a1badef69784.png");
            background-size: 100%;
        }
        .mmmaaaiinn{
            width:45%;
            margin-top: 10px;
            margin-bottom: 10px;
            padding-right: 15px;
            padding-left: 15px;
            margin-right: auto;
            margin-left: auto;
            background-color: white;
            opacity: 0.9;
            border-radius: 20px;

        }
        .UserDetiles {
            width: 70%;
            font-weight: bold;
            margin: 10px auto;
            text-align: center;
        }
        .UpdateInformation{
            width: 70%;
            border-style: ridge;
            border-radius: 20px;
            margin: 10px auto;
            text-align: center;
        }
        .DeleteUserProfile{
            width: 70%;
            border-style: ridge;
            border-radius: 20px;
            margin: 10px auto;
            text-align: center;
            height: 200px;
        }
        .ChangePassword{
            width: 70%;
            border-style: ridge;
            border-radius: 20px;
            margin: 10px auto;
            text-align: center;

        }
        .ChangeIcon{
            width: 70%;
            border-style: ridge;
            border-radius: 20px;
            margin: 10px auto;
            text-align: center;
        }
        #icon{
            display: inline-block;
        }
        .defaultAvartar{
            display: inline-block;
        }

    </style>
</head>
<body>



<jsp:include page="/WEB-INF/nav.jsp"/>
<div class="mmmaaaiinn">
<div class="UserDetiles">
<H2>Your Account</H2>
    <p>YOUR FIRSTNAME: ${userProfile.firstName}</p>
    <p>YOUR LASTNAME: ${userProfile.lastName}</p>
    <p>YOUR EMAIL: ${userProfile.email}</p>
    <p>YOUR DESCRIPTION: ${userProfile.description}</p>
    <p>YOUR BIRTHDAY: ${userProfile.birthDate}</p>
    <p>YOUR GENDER: ${userProfile.gender}</p>
    <p>YOUR AVATAR:</p>
    <img  id="icon1" src="${userProfile.avatar}"style="width: 100px">
</div>
<div class="UpdateInformation">

    <div>
        <h3>Update Details</h3>

        <form  class="ui form" id="profileForm" action="./Profile" method="post">
            <div>
                <div>
                    <label>Username:</label><input required  placeholder="Please type your userName " id="newUsername" type="text" pattern="[A-Za-z0-9-]{3,16}" name="username"
                                                   value="${userProfile.userName}" /><div id="messageText"></div></div>
                <div>
                    <label>First Name:</label><input required  type="text" placeholder="Please type your First Name. At least two characters." pattern="[A-Za-z]{2,16}" name="firstName" value="${userProfile.firstName}"/>
                </div>
            </div>

            <div>
                <div>
                    <label>Last Name:</label> <input required  placeholder="Please type your Last Name. At least two characters." type="text" pattern="[A-Za-z]{2,16}" name="lastName" value="${userProfile.lastName}"/></div>
                <div>
                    <label>Email: </label><input type="email" placeholder="Please type your email" name="email" value="${userProfile.email}"
                /></div>
                <div class="">
                    <label>Description: </label><input required  type="text" placeholder="Please type your description" name="description" value="${userProfile.description}"
                /></div>
            </div>
            <div class="two fields">
                <div class="field">
                    <label>Birthday:</label> <input required type="date" name="birthday" value="${userProfile.birthDate}"
                /></div>
                <div class="field">
                    <label>Gender:</label><select name="gender" id="gender">
                    <option value="Female">Female</option>
                    <option value="Male">Male</option>
                </select>
                </div>
            </div>
            <input  type="hidden" name="profileAction" value="Update" id="profileAction">
            <button class="ui submit button" id="updateBtn">Update</button>
        </form>
    </div>
</div>

<div class="ChangePassword" >
    <h3>Change Password</h3>
<form class="ui form" id="passwordForm" action="./ChangePassword" method="post" >
    <div >
        <label>Password: </label><input required  id="password" type="password" name="password"
                                        placeholder="Please Enter Your Password" />
    </div>
    <div >
        <label>New Password:</label> <input required  id="newPassword" type="password" name="newPassword"
                                            placeholder="Please Enter Your New Password. At least three characters." pattern="[A-Za-z0-9-]{3,16}"
    /></div>
    <div>
        <label>Re-enter New Password:</label> <input required  id="reNewPassword" type="password" name="reNewPassword"
                                                     placeholder="Please Re-Enter Your New Password" pattern="[A-Za-z0-9-]{3,16}"
    />
    </div>

    <button id="passwordBtn" class="ui submit button" type="submit" value="Update">Update</button>
</form>
</div>

<div class="ChangeIcon" style="display: block">
    <i class="close icon"></i>
    <div >
        <h3>Avatar</h3>
    </div>
    <div >

        <div >
            <div>
                <div >
                    <p>This is your current Avatar</p>
                    <img  id="icon" src="${userProfile.avatar}" style="width: 100px">
                </div>
                <div class="defaultAvartar">
                    <form action="./def" id="avatarForm1" method="post" >

                            <img src="DefaultAvatar/01.png" style="width: 50px"><br>
                            <input  hidden type="text" id="imageFile1" name="def" value="DefaultAvatar/01.png"/>
                            <input type="submit" class="ui submit button" value="choose">
                    </form>
                </div>
                <div class="defaultAvartar">
                    <form action="./def" id="avatarForm2" method="post" >

                        <img src="DefaultAvatar/02.png" style="width: 50px"><br>
                        <input  hidden type="text" id="imageFile2" name="def" value="DefaultAvatar/02.png"/>
                        <input type="submit" class="ui submit button" value="choose">
                    </form>
                </div>
                <div class="defaultAvartar">
                    <form action="./def" id="avatarForm3" method="post" >

                        <img src="DefaultAvatar/03.png" style="width: 50px"><br>
                        <input  hidden type="text" id="imageFile3" name="def" value="DefaultAvatar/03.png"/>
                        <input  type="submit"class="ui submit button" value="choose">
                    </form>
                </div>
                <div class="defaultAvartar">
                    <form action="./def" id="avatarForm4" method="post" >

                        <img src="DefaultAvatar/04.png" style="width: 50px"><br>
                        <input  hidden type="text" id="imageFile4" name="def" value="DefaultAvatar/04.png"/>
                        <input type="submit"class="ui submit button" value="choose">
                    </form>
                </div>
                <div class="defaultAvartar">
                    <form action="./def" id="avatarForm5" method="post" >

                        <img src="DefaultAvatar/05.png" style="width: 50px"><br>
                        <input  hidden type="text" id="imageFile5" name="def" value="DefaultAvatar/05.png"/>
                        <input type="submit" class="ui submit button"value="choose">
                    </form>
                </div>

                    <form action="./AvatarEdit" id="avatarForm" method="post" enctype="multipart/form-data">
                        <input required type="file" id="imageFile" accept=".jpg, .gif,.png"
                               name="icon"/>
                        <input type="submit" name="result" class="ui submit button" id="result" value="upload">
                    </form>

                </div>
            </div>
        </div>
    </div>

<div class="DeleteUserProfile">
    <h3>Think Twice Before you do below</h3>
    <br>
    <br>
    <br>
    <br>
    <form  id="profileForm1" action="./Delete" method="post">
        <button  type="submit" class="ui red button submit" id="deleteBtn">Delete Your Profile</button>
    </form>
</div>
</div>
</body>
</html>
