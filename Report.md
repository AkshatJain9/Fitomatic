# Team Fitomatic Report - AJSMDLLZ

**Problem Statement:**  
Spending over two years in lockdown under various restrictions has made it very difficult to do two things, socialise and exercise. Our app helps promote social good by facilitating an environment that encourages an active and healthy lifestyle. Fitomatic is a social exercising app, where users are able to interact with others in an active environment. 


## Table of Contents

1. [Team Members and Roles](#team-members-and-roles)
2. [Summary of Individual Contributions](#summary-of-individual-contributions)
3. [Conflict Resolution Protocol](#conflict-resolution-protocol)
4. [Application Description](#application-description)
5. [Application UML](#application-uml)
6. [Application Design and Decisions](#application-design-and-decisions)
7. [Summary of Known Errors and Bugs](#summary-of-known-errors-and-bugs)
8. [Testing Summary](#testing-summary)
9. [Implemented Features](#implemented-features)
10. [Team Meetings](#team-meetings)

## Team Members and Roles

| UID | Name | Role |
| :--- | :----: | ---: |
| u7283219 | Shaazaan Majeed | Backend Logic, Software Testing |
| u7284728 | Deni Lanc | Backend Logic, Software Testing |
| u7284072 | Akshat Jain | Data management (Firebase) |
| u7285188 | Leon Zolati | UI Design + Development |

## Summary of Individual Contributions

**u7284728, Deni Lanc**, I contributed 25% of the code. Here are my contributions:
* xml files:
  * fragment_individual_post.xml 
  * fragment_small_post.xml
  * login_success_list_user.xml (Removed)
  * activity_user_profile.xml (Removed)
* SmallPostFragment.java
* LargePostFragment.java
* IndividualPostFragment.java
* UserProfile.java (Removed)
* FeedAdapter.java (Removed)
  * Was used to originally display the users on the main feed page but we changed it to posts.
* TokenizerTest.java
* MessageRecyclerAdapter.java
  * Block button handler
* RecycleFeedAdapter.java
  * Individual, Small and Large like and follow handlers
* Email.java (Removed)
  * Was a singleton design pattern but we ended up not needing it
* Report writing sections written
  * Wrote meeting minutes 2 and 3
  * Testing Summary
  * Data Structures
  * Tokenizer and Parser
  * Design Patterns
<br>

**u7285188, Leon Zolati**, I contribute 25% of the code. Here are my contributions:
* All xml files with the following exceptions (only small alterations made to layout and bugfix): 
  * fragment_individual_post.xml 
  * fragment_large_post.xml
  * fragment_small_post.xml
* RecycleFeedAdapter.java
* MessageRecyclerAdapter.java
* DirectMessageAdapter.java
* hostActivity.java
  * Created the bottom navigation menu and corresponding fragments and refactored old activities to work as fragments (changes made due to different life cycle of fragments, ways of getting the context, etc.)
* PostHostActivity.java
  * Created the navigation menu including scaffolding for the fragments but did not actually implement the forms front end.
* Other small additions like adding the adapters to recycleView’s
*Other contributions:*
* Use of Figma to do the following:
  * Creation of a style guide
  * Creation of Logo
  * Creation of User Flow (how a user will go through the app which pages lead to where)
  * Creation of Icons
* Created and will present the Minute Madness presentation
<br>

**u7283219, Shaazaan Majeed**, I contributed approximately 25% to the project. The following are my contributions:
Heavily involved with most aspects of the project. Including overall code design and implementation, report writing, software testing, proposing various design patterns and implementing a factory method.
* Code Implementation: Created the factory design pattern for standardising post creation between three concrete post classes.
  * Post.java
  * SingleActivity.java
  * SmallGroupActivity.java
  * EventActivity.java
  * PostFactory.java
* Code Implementation: Created original Tokenizer with deprecated grammar, which was expanded on by Akshat. Git commit version (https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/tree/a4e9d9effc0f154356f2fa11c8f167cccdbba609/app/src/main/java/com/ajsmdllz/fitomatic)
  * Token.java
  * Tokenizer.java (Removed)
  * SimpleTokenizer.java (Removed)
* Code Implementation: Software tests created
  * AVLPostsTest.java
  * MessageTest.java
  * ParserTest.java
  * PostFactory.java
  * TokenTest.java
* UI Design: Created UI elements xml files:
  * fragment_large_post.xml 
* Report writing sections written
  * Team Members and Roles
  * Conflict Resolution Protocol
  * Application design and use cases
  * Summary of known errors and bugs
  * Team meetings
* Miscellaneuos:
  * Created and maintained the group discord server for project management and team communications.
<br>

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

## Conflict Resolution Protocol

1. A neutral third party will be agreed upon by both parties to mediate the conflict. Given that each member is responsible for different sections of the app, a mediator should be found within the group itself, however, if this is not an option, the parties can agree to have a mediator outside the group. 
2. Each party will work together with the mediator to define the conflict in their own words, constructing a single definition of the conflict. 
3. A list of positives and negatives outlining the key points of issue causing the conflict will be created. These should be considered relative to the specifications of the assignment as well as practical concerns regarding the usability and our workload. 
4. The problem will be resolved. The goal of the mediation is for consensus to be achieved through compromise.If consensus cannot be reached, a decision will be made by a majority vote by the entire group.

## Application Description

Fitomatic is a social media application encouraging users to socialise in a group or one-on-one fitness activity. Users are able to view, follow, like and search from thousands of different posts on the home feed. These posts include the activity participants will do, when and where it will occur along with additional information. Posts are broken down into three distinct categories an individual post (one-on-one fitness activity), a small group post and a large group post targeted for big events and fundraisers.

Users first create a profile by completing their login credentials then select various active interests. Finally completing their profile with identifiable information such as name, gender, age and a description. The application involves four main fragments, the home feed, a peer to peer messaging service, create a post and profile view. From there, users can follow posts, message individuals, create their own events and view their list of following posts on their profile page. 

On the home feed, users are able to view, follow, like and search from thousands of different posts. On the peer-to-peer messaging fragment users are able to individually message other users as well as being able to block others which will remove them from the messaging view. On the post fragment, users can view their own created posts as well as create new posts. When a user creates a new post, a fragment is displayed where individual, small group or large group posts can be selected. Each post type has differences between them and with the use of a factory method, post creation is standardised. Finally, on the profile fragment, users can view their profile picture and name towards the top of the screen and view their following posts below.


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

**Design Patterns**
We have used three design patterns namely, the factory, singleton and iterator design patterns. 

The **factory** design pattern is made up of classes PostFactory, SingleActivity, SmallGroupActivity, and EventActivity. It is used in the classes IndividualPostFragment, LargePostFragment, ProfileFragment, and SmallPostFragment to create a certain type of post depending on what is given to the factory method. This design pattern was chosen because it allowed deferred instantiation of a subclass, giving the ability to delegate to the relevant helper subclass. This is important when creating and retrieving posts from Firebase as PostFactory can be used to figure out what kind of post we want to store/display. 

The **singleton** design pattern was used in DBQuery which takes in an expression object generated by the parser and outputs a Firestore Query object to be passed into the database reference. This class essentially acts like an input handler with only one “black-box” function, which has no immediate side-effects. Hence, since its functionality does not change based on any instantiation, it made sense that we would only ever need one instance of DBQuery. So, it was implemented as a Singleton, thus saving unnecessary memory usage and increasing efficiency.

An **iterator** design pattern was used within the tokeniser to simplify the logic when parsing. This included a method to get the current token, go to the next token, and check if another token exists. These functions abstracted the iterative logic for checking if the input string conforms to the grammar to the tokeniser, making parsing simpler and more concise. The simplifications that this design approach provided is outlined in detail in the code smells portion. In sum, the code is much more “natural” in that it can be clearly mapped to the logic outlined in the grammar without unnecessary intermediate steps.

**Grammar(s)**

Production Rules
<br> \<Statement> => userExp(user, \<Activity>) | \<Activity>
<br> \<Activity> => ActivityQueryExp(activity, \<Activity>) | \<Fields>
<br> \<Field> => PostQueryExp(title, \<Fields>) | \<Time>
<br> \<Time> => TimeExp(date, EmptyExp) | EmptyExp

The following is the search grammar which the parsed expression must conform to. It is ordered by what users would naturally search for. Importantly, the users should be able to search for multiple activities and multi-worded titles. Hence, the grammar is self recursive in these two fields. If a user wants to search for a user, it seems natural that this would be the first thing they search for. Finally, the time allows users to narrow down searches further (or search for themselves in entirety) so it was placed last. The modular design of the Grammar is also easily extensible to add for more fields later if needed. This is mainly due to the fact that it is a purely linear grammar opposed to a circular one. The design is also extremely helpful when constructing the Query object as its nested structure allows queries to be built upon. Overall, the design suits the use case and is easy to use.

A downside is that due to some of the limitations of Firebase (e.g. not being able to search with OR logic instead of AND, not being able to use whereIn() for multiple fields), the design is slightly specialized towards Firebase. This reduces the modularity of our overall project. Hence, this could be fixed, however, we would need more information on the other types of systems we wish to use.

**Tokenizer and Parsers**

The tokenizer and parser is utilised when a user wants to search for a post on the main page of the app. A user is able to search for specific titles, activities, users, and time on the search bar at the top of the page. This first starts with the user entering a search string which is partitioned by word within the tokeniser. Then, for each word, a token containing the string is created by searching for specific characters (e.g. @ for users), specific words (activity names), and other attributes to get an iterable token collection. Note that this is stored in an iterator pattern as this eases the logic for constructing a grammar. These tokens are then parsed by the parser which generates an expression object which specifies how a search query should be structured. The grammar by which the parser does this is explained below. Finally, a specialised singleton class, DBQuery, is used to interpret this expression to a Firestore Query object which can simply be called.

Key advantages of our approach mainly include the separation of concerns in different stages of the search mechanism. As a result, we could try different methods of parsing, tokenizing, and query processing to optimise the process. It also increases efficiency and readability as each class in the process has a specialised role. The generalised nature of the parser further helps in querying the most relevant posts as we make use of Firestore Query’s in-built functions to iteratively build up a query from the expression.

**Surpise Item: Code Smells**

Four **Existing** Code Smells:
1. Encapsulation Smell: Querying Posts to Factory
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/f85177669e02edae305b7018eff8f84ebb96c508/app/src/main/java/com/ajsmdllz/fitomatic/ui/home/HomeFragment.java#L87-130
2. Modularisation Smell: Iterating over Tokens split into 2 classes
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/f85177669e02edae305b7018eff8f84ebb96c508/app/src/main/java/com/ajsmdllz/fitomatic/Search/SearchTokenizer.java
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/f85177669e02edae305b7018eff8f84ebb96c508/app/src/main/java/com/ajsmdllz/fitomatic/Search/SearchParser.java
3. Abstraction Smell: Directly accessing user firebase object in line of code, could be abstracted to another class to act as interface to database.
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/f85177669e02edae305b7018eff8f84ebb96c508/app/src/main/java/com/ajsmdllz/fitomatic/Registration/ProfileCreation.java#L98-105
4. Modularisation Smell: DBQuery is static so can’t extend or implement additional features
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/f85177669e02edae305b7018eff8f84ebb96c508/app/src/main/java/com/ajsmdllz/fitomatic/Search/DBQuery.java

**Corrections:**
1. Fixed Encapsulation: Abstracted Query Logic to Factory Class
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/commit/1c7626624e58d739b9bf83511fa3605e61c0b6cb 
2. Fixed Modularisation: Moved iterator logic for parser into tokeniser class
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/commit/3df0dc26727d59bcf15266c315c7d88e1f01f643 
3. Fixed Modularisation: Made DBQuery a singleton so it can be extended in the future:
   * https://gitlab.cecs.anu.edu.au/u7284072/comp2100-group-assignment-ajsmdllz/-/blob/master/app/src/main/java/com/ajsmdllz/fitomatic/Search/DBQuery.java

**Explanations:**
1. Our first smell was an encapsulation smell regarding the mechanism we use to query posts for display on our main feed. The following link shows that when obtaining the posts from the database, the firebase object mapping is manually loaded and converted within the home fragment class. However, this piece of logic has nothing to do with the HomeFragment’s mechanism and is closer to the functionality of the post factory class. Considering this **exact** portion of code will need to be repeated to display the followed posts on the profile fragment, this section should be encapsulated to the PostFactory class.
2. The following two files show the mechanism for tokenizing and parsing. We importantly see that the **Parser** is responsible for iterating over the **Tokenised** strings which is another class. This miss-match of delegation of iterative logic is a smell as it reduced modularity of the two classes since they are now independent. Hence, some mechanism should be added within the SearchTokeniser to encapsulate the iterator logic within itself. This was done by adding an iterator design pattern to the tokeniser class.
3. The third smell is an abstraction smell where the user object that is stored in the firebase is directly updated within the ProfileCreation class which is instead responsible for the UI logic when creating the user. This could be abstracted into its own class which is responsible for updating the user fields in the database. However, given that this interaction was only done once with these specific fields, we found that this abstraction was somewhat unnecessary. If we were to scale our application to more features however, this would be an appropriate candidate for refactoring.
4. The fourth and final smell involves the DBQuery class being static, meaning it cannot extend or implement non-static features, reducing its modularity. Given that DBQuery is responsible for constructing a query object, we would perhaps like it to be extendable in the future if we want to implement a more complicated search mechanism in the future or add any additional features regarding query optimisations etc. Hence, to fix this, we implemented the class as a singleton so it can be easily extended, without compromising its functionality.

**2500 Valid data instances**

Within the firestore database, we have added a total of 2500 data instances. This includes users, posts, messages, blocks, follows, user deletions and more. Hopefully this is able to simulate an app feed for the user! You will be able to see posts, ordered by likes which have already been added, profiles made, and be able to message other users. You will also be able to test how the app handles large data (such as loading posts in the tree) and objects from the database. 

## Summary of Known Errors and Bugs

1. Loading Profile Picture
   * There is a small delay when you start the “ProfileFragment.java” activity and the user profile picture displaying. This does not cause application issues just a minor inconvenience.
2. Following deleted posts
   * If a user follows a post and it is then deleted the user will not be able to view their profile page without the app crashing. (Note we haven’t actually implement the ability to delete posts this is just an issue if it were to be implemented)
3. Rapidly switching between the fragments in the navigation
   * If a user rapidly switches between the fragments in the bottom navigation bar, the app crashes. Is not replicated on the post creation navigation bar and so is likely an issue with firebase e.g. too many requests to handle.
4. Creating a new user and not finishing the process
   * If a user creates a new email and password but doesn’t fill out the rest of the registration and back out, the user will not be able to access their profile and other without the app crashing. This may also have consequences for other users without deleting the erroneous user.

## Testing Summary

AVLPostTest.java
  * Number of test cases: 9
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:  
  <img src="/images/ReportImgAssets/TestImages/avlTest.png" width="700" height="52"><br>
  * Types of tests created:
    * Null 
    * Height 
    * Insert 
    * Complex insert <br>

TokenTest.java
  * Number of test cases: 18
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:   
  <img src="/images/ReportImgAssets/TestImages/tokenTest.png" width="700" height="52"><br>
  * Types of tests created:
    * .getToken
    * .getType
    * Equality tests
    * toString <br>

TokenizerTest.java
  * Number of test cases: 6
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:  
  <img src="/images/ReportImgAssets/TestImages/searchTokTest.png" width="700" height="52"><br>
  * Types of tests created:
    * Correct title, time, user, activity and combined <br>

PostFactory.java
  * Number of test cases: 12
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:  
  <img src="/images/ReportImgAssets/TestImages/factoryTest.png" width="700" height="52"><br>
    * Note: Only tested relevant fields as there are elements related to Firebase which cannot be tested.
  * Types of tests created:
    * Single, small, event and event edge cases test. <br>

ParserTest.java
  * Number of test cases: 13
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:  
  <img src="/images/ReportImgAssets/TestImages/searchTest.png" width="700" height="128"><br>
  * Types of tests created:
    * Testing simple title, time, user, activity
    * A mid case and two complex cases with multiple activities and a user, time and also using .toString <br>

MessageTest.java
  * Number of test cases: 6
  * Code coverage: Used IntelliJ Run with Coverage
  * Image:  
  <img src="/images/ReportImgAssets/TestImages/messageTest.png" width="700" height="46"><br>
  * Types of tests created:
    * .getMessage and .getSender

**White-Box Testing - UI**

We further took a systematic approach to testing each UI component. We were able to use some of the same principles we learned in lectures and apply them to the UI control flow.

For instance, when registering a user, in the first pane we have 3 input fields. For testing, we tried having every permutation of valid/invalid inputs to test if they would create the correct toast. Similarly, when selecting activities, uploading profile photos and entering the name/age/bio, we could vary each of these inputs as valid inputs or invalid/non-existent. Hence, in doing so, we were able to replicate a sort of “branch coverage” when testing the UI control flow for the registration process.

A similar approach was taken when testing the create post classes. For each type of post, we tried creating activities with invalid/valid field combinations to make sure no bogus post was stored in the database. Contrasting to the registration however, this testing was more aligned to make sure our database code was functioning. 

We further tested the searching mechanism by creating multiple posts with different field names by different users. We then tested searching by user, activities, multi-word titles etc. and then made sure that the relevant posts that we made showed up. This was again testing out firebase code (mainly DBQuery.java) to make sure that our interactions with the database was correct.

Hence, using these techniques, we were able to demonstrate our ability to take the content we learned in lectures and extend it to aspects of our application that could not be covered by JUnit in an efficient manner. It also allowed us to fix countless bugs and firebase mechanisms.

**Testing Conclusion:**  
Counting both our JUnit tests and our systematic testing of the app’s firebase functionality, we were able to achieve a test coverage of over 70% excluding UI. We were importantly able to maintain confidence that our app worked as intended (excluding the bugs we found through testing) and achieve the goals we set out to do. Hence, testing was a highly helpful and enjoyable section.


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

We have implemented a total of nine different application features broken down into one ‘hard’, three ‘medium’ and five ‘easy’ features. Listed below as follows:

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
<br><img src="/images/ReportImgAssets/featureImages/examplePost.png"><br>

(3) Feature 4: The ability to ‘follow’ users, events, movements, hashtags, topics. There must be a section specifically dedicated to 'things' followed. **(medium)**
   * Users can follow posts they are interested in from the main page by clicking the ‘Follow’ button. Followed posts are visible from the user's profile page. The Profile Image below shows an example of the user ‘Joanna Brick’ who has followed a post with the title ‘BowlingTrip’ and is visible on their profile page.
<br><img src="/images/ReportImgAssets/featureImages/userProfile.png"><br>

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
<br><img src="/images/ReportImgAssets/featureImages/messages.png"><br>

**Firebase Integration:**<br>
(1) Feature 8: Use Firebase to implement user Authentication/Authorisation. **(easy)**
   * Firebase Authentication was implemented using the FirebaseAuth object. In the registration page, the email and password (which was checked for validity beforehand) was passed into the “createUserWithEmailAndPassword” function. This was tied to the creation of the user object in the firestore database which similarly used the inbuilt functions. To make use of the document-oriented database, we first chose the sign-up email as the “primary-key” for accessing and indexing user data. To stop creating multiple accounts with the same email, we could simply check the firestore database to see if a document with the specified email exists and handle that case appropriately. For authorisation, we again used Firebase Authentication with the function “signInWithEmailAndPassword” to verify users. Entry upon the app was conditional on this function executing correctly.

(2) Feature 9: Use Firebase to persist all data used in your app (this item replaces the requirement to retrieve data from a local file) **(medium)**
   * We used Firebase for our data storage purposes for things such as storing users and posts. Each user stored in Firebase has many attributes, some can be seen in the User Info Images. Every post also has many relevant fields related to that post such as how many likes it has and the activities associated with it. Posts are stored with their primary key formatted as (User that created the post, Number of posts this user has made). Users primary key is their email address as they are unique to each user. 
<br><img src="/images/ReportImgAssets/featureImages/persistData.png"><br>


## Team Meetings

Meeting Minutes

- *[Team Meeting 0](./meetings/Meeting0_6-4-22.md)*
- *[Team Meeting 1](./meetings/Meeting1_9-4-22.md)*
- *[Team Meeting 2](./meetings/Meeting2_13-4-22.md)*
- *[Team Meeting 3](./meetings/Meeting3_18-4-22.md)*
- *[Team Meeting 4](./meetings/Meeting4_23-4-22.md)*
- *[Team Meeting 5](./meetings/Meeting5_1-5-22.md)*
- *[Team Meeting 6](./meetings/Meeting6_5-5-22.md)*
- *[Team Meeting 7](./meetings/Meeting7_16-5-22.md)*

Note: smaller informal meetings were also conducted however these did not require minutes to be taken.
