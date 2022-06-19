# Team Fitomatic Report - AJSMDLLZ

**Problem Statement:**  
Spending over two years in lockdown under various restrictions has made it very difficult to do two things, socialise and exercise. Our app helps promote social good by facilitating an environment that encourages an active and healthy lifestyle. Fitomatic is a social exercising app, where users are able to interact with others in an active environment. 


## Summary of Individual Contribution

**u728072, Akshat Jain**, I contributed approximately 25% of the code. The following are my contributions:
* Set up and configured Firestore Database, Image Storage Database and User Authentication Database for use.
* Created all of the Peer-to-Peer Messaging mechanism from the back-end, including reading from input fields, updating the firebase, and retrieving the messages previously sent. This also included filtering blocked users. I then later worked with Leon to transfer all of this logic to Recycle Feeds so that it would fit the UI theme. The following was written by myself;
  * DirectMessage.java (excl. populateMessages)
  * MessageFragment.java
* Inspired heavily by the initial tokeniser logic created by Shaazaan, I created the full search mechanism, including the Tokeniser (with the Iterator Design Pattern), Parser (including the Grammar) and DBQuery.java which creates a Firestore Query Object. So in total, the following classes.
  * DBQuery.java
  * Expressions Folder
  * KeywordList.java (Used in Tokeniser)
  * Token.java
  * SearchParser.java
  * SearchTokenizer.java (Built from Shaazaan’s initial version)
* Storing all user data in Firestore store database in the registration phase. This also included storing the user profile picture in a separate database.
  * ChooseInterests.java (Line 31-43)
  * Registration.java
  * ProfileCreation.java (Excl. onCreate())
* Created a mechanism for querying posts from the database to be displayed on the main feed, the profile page (following posts), the user’s own posts. Similarly, also added a mechanism to retrieve all user objects for display on the message fragment.
  * PostFactory.java (createPostfromDBSnapshot & createPostfromDBQuerySnapshot)
  * HomeFragment.java (76-86, 120-125)
  * ProfileFragment.java (133-147)
  * MessageFragment.java (73-105) 
* Created the AVL tree which stores the posts queried in the database in a sequential matter (from most likes to least) in an efficient manner. Also made this data structure usable with the UI (working with Leon)
  * AVLPosts.java
  * HomeFragment.java (120-125)
* Created a mechanism to send direct notifications to a user's device using the Firebase Messaging service. This was ultimately removed as it did not contribute to our overall app design

## Application Description

Fitomatic is a social media application encouraging users to socialise in a group or one-on-one fitness activity. Users are able to view, follow, like, and search from thousands of different posts on the home feed. These posts include the activity participants will do, when, and where it will occur along with additional information. Posts are broken down into three distinct categories: an individual post (one-on-one fitness activity), a small group post, and a large group post targeted for big events and fundraisers.

Users first create a profile by completing their login credentials then select various active interests. Finally, completing their profile with identifiable information such as name, gender, age and a description. The application involves four main fragments, the home feed, a peer to peer messaging service, create a post, and profile view. From there, users can follow posts, message individuals, create their own events, and view their list of following posts on their profile page. 

On the home feed, users are able to view, follow, like, and search from thousands of different posts. On the peer-to-peer messaging fragment users are able to individually message other users as well as being able to block others which will remove them from the messaging view. On the post fragment, users can view their own created posts as well as create new posts. When a user creates a new post, a fragment is displayed where individual, small group, or large group posts can be selected. Each post type has differences between them and with the use of a factory method, post creation is standardised. Finally, on the profile fragment, users can view their profile picture and name towards the top of the screen and view their following posts below.


Fitomatic is a gateway to socialise with others while in an active environment promoting a healthy lifestyle. 

<div align="center">
<img src="/images/ReportImgAssets/profileCreationProcess.png" width="700" height="421">
<img src="/images/ReportImgAssets/fourMainFragments.png" width="700" height="381">
<img src="/images/ReportImgAssets/threePostTypes.png" width="700" height="480">
</div>
<br>

**Application Use Cases**

Fitomatic is targeted toward people of all ages. Individual users looking for a partner in their exercising routines or small group sporting activities as well as catering for large events directed towards fundraisers. On a social level, we want to promote physical activity for all ages. Especially for younger people, who seem to have health problems earlier on.

Timmy wants a buddy to go on afternoon runs with:
   * Timmy makes an individual post stating he wants a running partner who is available for afternoon runs.
   * Other users can follow/like the post to get in contact with Timmy.
   * After choosing a running partner Timmy can send over more details such as the location and equipment required through the peer to peer messaging service.
   * After the activity concludes Timmy can keep in touch with the peer to peer messaging service or accept other users.

Steve wants to play a game of soccer with others:
   * Steve creates a small group post outlining where and when he would like to play soccer.
   * Other users can follow/like the post to save it if they want to participate.
   * Steve can now enjoy his game of soccer with the other users.
   * Participants of the soccer match can keep in touch with the peer to peer messaging service to organise future plans.

Fun Run ACT wants to create a fundraising running marathon:
   * Fun Run ACT can create an account with appropriate verifiable credentials
   * Fun Run ACT can create a large group post stating all necessary information: when, where, cost of entry for the fundraising event.
   * After the fundraising marathon Fun Run ACT can contact event winners and other users who want to interact with the organisation can through the peer to peer messaging service.

## Application UML
**Front End UML Diagram:**  
![Front End UML](/images/ReportImgAssets/frontEndUML.png)

**Back End UML Diagram:**  
![Back End UML](/images/ReportImgAssets/backEndUML.png)


## Application Design and Decisions
**Data Structures**
We used the following data structures in my project:
1. ArrayList
   * Objective: It is used for storing interest, posts, blocked users and following posts for our Users. It is also used for storing liked posts, followers and activities for our Posts and messages in DirectMessage.java.
   * Locations: User.java, Post.java, EventActivity.java, SmallGroupActivity.java, DirectMessage.java, PostFragment.java, ProfileFragment.java
   * Reasons:
     * Easily resizable if a user wants to like/follow a post or block a user.
     * Similarly useful for what we want out of our Posts, constantly changing values and sizes.
2. HashMap
   * Objective: It is used for our peer to peer messaging service. 
   * Locations: User.java
   * Reasons:
     * We decided to store messages as a component of the user object.
     * Hence for a given conversation with user X, we would have an arraylist of messages, Y
     * Hence, there is a natural mapping between the user email string and the messages between them. Hence, it felt natural to use a HashMap
     * It is also efficient when querying and storing message objects as user emails provide a direct and efficient indexing key to access firestore objects, and it doubles as the key of the hashmap.
3. List
   * Objective: It is used for displaying text messages through our P2P messaging system.
   * Locations: DirectMessageRecyclerAdapter.java
   * Reasons: 
     * Used to sequentially display messages so we can easily iterate over elements in the List and display them.
4. AVL Tree
   * Objective: It is used to sort the feed on the main page of the app by the number of likes a post has.
   * Locations: AVLPosts.java
   * Reasons:
     * Using a standard linear iterable type such as an array leads to O(n) time complexity for post insertion if we want the posts to be ordered from most liked to least liked. If there are n posts, this leads to a worst case scenario of O(n^2).
     * Using a tree lowers insertion time to O(log n) giving a worst case of O(n log n) which is significantly better. 
     * Hence AVL trees are a valuable addition to the application as it allows us to display posts much faster.
     

**Tokenizer and Parsers**

The tokenizer and parser is utilised when a user wants to search for a post on the main page of the app. A user is able to search for specific titles, activities, users, and time on the search bar at the top of the page. This first starts with the user entering a search string which is partitioned by word within the tokeniser. Then, for each word, a token containing the string is created by searching for specific characters (e.g. @ for users), specific words (activity names), and other attributes to get an iterable token collection. Note that this is stored in an iterator pattern as this eases the logic for constructing a grammar. These tokens are then parsed by the parser which generates an expression object which specifies how a search query should be structured. The grammar by which the parser does this is explained below. Finally, a specialised singleton class, DBQuery, is used to interpret this expression to a Firestore Query object which can simply be called.

Key advantages of our approach mainly include the separation of concerns in different stages of the search mechanism. As a result, we could try different methods of parsing, tokenizing, and query processing to optimise the process. It also increases efficiency and readability as each class in the process has a specialised role. The generalised nature of the parser further helps in querying the most relevant posts as we make use of Firestore Query’s in-built functions to iteratively build up a query from the expression.


## Implemented Features

Base Feautures:
   * Users are able to login and sign up using Firebase authentication, more details explained below
   * Users are able to load and upload data to Firebase through Posts, peer to peer messaging and can be visualised through the four main fragments.
   * Users are able to search for specific Posts on the home feed through a Tokenizer and Parser via the formal grammar explained above.
   * Within the firestore database, we have added a total of 2500 data instances. This includes users, posts, messages, blocks, follows, user deletions and more. <br>

Underlying Implementation:
   * Fully implemented AVL tree used to sort the feed on the main page of the app by the number of likes a post has.
   * Used three design patterns, Singleton, Factory, Iterator as stated above.
   * The use of a local file was overwritten by persisting all data on FireBase

We have implemented a total of **nine** different application features broken down into one ‘hard’, three ‘medium’ and five ‘easy’ features. Listed below as follows:

**UI Design:**<br>
(1) Feature 1: UI must have portrait and landscape layout variants as well as support for different screen sizes. **(easy)**
   * All UI XML files were created with relative constraint layouts. This allowed for versatility for all screen sizes and layout variants. Not hard coding the UI design for a single screen variant allowed for a range of different screen sizes as well as different orientations. Some pages have a landscape variant due to auto rotation providing a poor outcome. This was verified by running our app on various different emulators as well as physical devices with different screen layouts.

**Greater Data Usage, Handling and Sophistication:**<br>
(2) Feature 2: User profile activity containing a media file (image, animation, video). **(easy)**
   * When a user is creating their profile, they have the option to add a profile picture indicated by the add icon in the top left-hand corner. This image is then stored on a Firebase Storage database and is linked to their respective user ID. On the user profile activity fragment, users are able to see their profile picture from a Firebase image retrieval query. From the images below we can see the user has added a soccer ball as their profile picture.  
<br><img src="/images/ReportImgAssets/featureImages/addProfilePic.png" width="700" height="202"><br>

**User Interactivity:**<br>
(1) Feature 3: The ability to micro-interact with items in your app (e.g. like/dislike/support/report a post/message/event). **(easy)**
   * Users are able to like and follow posts as well as message other users and block users from messaging them. If a user likes a post, it updates the count of likes within the specific post which is then used to order the post within the feed.
<br><img src="/images/ReportImgAssets/featureImages/examplePost.png" width="450" height="295"><br>

(3) Feature 4: The ability to ‘follow’ users, events, movements, hashtags, topics. There must be a section specifically dedicated to 'things' followed. **(medium)**
   * Users can follow posts they are interested in from the main page by clicking the ‘Follow’ button. Followed posts are visible from the user's profile page. The Profile Image below shows an example of the user ‘Joanna Brick’ who has followed a post with the title ‘BowlingTrip’ and is visible on their profile page.
<br><img src="/images/ReportImgAssets/featureImages/userProfile.png" width="400" height="439"><br>

**Privacy:**<br>
(5) Feature 5: Provide users with the ability to ‘block’ things. Things (e.g., events, users, messages containing abusive language, etc) shall not be visible to the user who blocked that activity. **(medium)**
   * Within the message tab, each user is coupled with a block button. Upon clicking the block button, the email of the user they want to block is added to the “blocked” field in the user class, which is stored on the firestore database. When the list of users is queried, users with emails in this array are filtered out. Hence, the user no longer sees the blocked user on their feed, nor do they see their messages in any form. Again, using the email as the blocking metric as it is the most efficient way to identify a user who has been blocked, as it is the “primary-key”
<br><img src="/images/ReportImgAssets/featureImages/blockingUser.png"><br>

**Creating Processes:**<br>
(2) Feature 6: Process visualisation. Your app may implement a graphical element to visualise the progress of a process/event. **(easy)**
   * Our app implements a graphical element to visualise the progress of creating a new user. The process involves three stages, setting up basic authentication information, email, and password. Then choosing activities/interestes while finally adding more data to your profile such as a profile picture, name, gender, age, and a description about yourself. This process of creating a user is visualised by a circle filling up in the top right-hand corner. The circle indicates the progress the user has made in completing their profile. (shown below)
<br><img src="/images/ReportImgAssets/featureImages/progressBar.png"><br>

**Peer to Peer Messaging:**<br>
(1) Feature 7: Provide users with the ability to message each other or an institution directly (e.g., a user can message an event/movement that is managed by another user). **(hard)**
   * On the messages tab, a user has the option to message any other user (which they have not blocked). The users are displayed in accordance with their first and last names. When a user-name is clicked, a new activity (DirectMessage.java) is opened with the recipient’s email in the intent. Previous messages are loaded and displayed accordingly. When a user types and sends a message using the input field, the string is first created into a Message object which additionally has the sender’s email. It is then stored in the database as a part of the “messages” field in the user classes where the recipient is treated as a “key” to the conversation. The conversation itself is stored as an arraylist of messages. The message is also added to the recipient’s user object as well. This design allows for precise querying when loading messages as we can use the previously designed email keys for direct access. The use of arraylist was also seen as the most efficient data structure to store the messages as we are reading and writing at similar frequencies. 
<br><img src="/images/ReportImgAssets/featureImages/messages.png" width="450" height="191"><br>

**Firebase Integration:**<br>
(1) Feature 8: Use Firebase to implement user Authentication/Authorisation. **(easy)**
   * Firebase Authentication was implemented using the FirebaseAuth object. In the registration page, the email and password (which was checked for validity beforehand) was passed into the “createUserWithEmailAndPassword” function. This was tied to the creation of the user object in the firestore database which similarly used the inbuilt functions. To make use of the document-oriented database, we first chose the sign-up email as the “primary-key” for accessing and indexing user data. To stop creating multiple accounts with the same email, we could simply check the firestore database to see if a document with the specified email exists and handle that case appropriately. For authorisation, we again used Firebase Authentication with the function “signInWithEmailAndPassword” to verify users. Entry upon the app was conditional on this function executing correctly.

(2) Feature 9: Use Firebase to persist all data used in your app (this item replaces the requirement to retrieve data from a local file) **(medium)**
   * We used Firebase for our data storage purposes for things such as storing users and posts. Each user stored in Firebase has many attributes, some can be seen in the User Info Images. Every post also has many relevant fields related to that post such as how many likes it has and the activities associated with it. Posts are stored with their primary key formatted as (User that created the post, Number of posts this user has made). Users primary key is their email address as they are unique to each user. 
<br><img src="/images/ReportImgAssets/featureImages/persistData.png" width="700" height="410"><br>
