# Team ACE Report - AJSMDLLZ

**Problem Statement:**  
After spending over two years in lockdown under various restrictions it has made it very difficult to socialise with others and leave the house. Our app helps promote social good by facilitating an environment that encourages an active and healthy lifestyle. Fitomatic is a social exercising app, where users are able to interact with others in an active environment. 


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

*[Summarise the contributions made by each member to the project, e.g. code implementation, code design, UI design, report writing, etc.]*

*[Code Implementation. Which features did you implement? Which classes or methods was each member involved in? Provide an approximate proportion in pecentage of the contribution of each member to the whole code implementation, e.g. 30%.]*

*Here is an example:*

*UID1, Name1, I contribute 30% of the code. Here are my contributions:*
* A.class
* B.class: function1(), function2(), ...
* ....

*[Code Design. What design patterns, data structures, did the involved member propose?]*

*[UI Design. Specify what design did the involved member propose? What tools were used for the design?]*

*[Report Writing. Which part of the report did the involved member write?]*

*[Slide preparation. Were you responsible for the slides?]*

*[Miscellaneous contributions. You are welcome to provide anything that you consider as a contribution to the project or team.]*

## Conflict Resolution Protocol

Find a neutral member for the given issue to delegate a discussion between the disagreeing parties. Construct a positives and negatives list outlining the key aspects of the conflict, combine ideas where possible and make compromises to solve the issue. However, if the conflict still continues hear all sides of the argument in full and hold a final group vote where a solution will be made based on assignment specifications and mutual compromise.

## Application Description

Fitomatic is a social media application encouraging users to socialise in a group or one-on-one fitness activity. Users are able to view, follow, like and search from thousands of different posts on the home feed. These posts include the activity participants will do, when and where it will occur along with additional information. Posts are broken down into three distinct categories an individual post (one-on-one fitness activity), a small group post and a large group post targeted for big events and fundraisers. 

Users first create a profile by completing their login credentials via an email and password then select various active interests. Finally completing their profile with identifiable information such as name, gender, age and a description. After completing a profile users are able to fully utilise the application. The application involves four main fragments, the home feed, a peer to peer messaging service, create a post and profile view. From there users can follow posts, message individuals, create their own events and view their list of following posts on their profile page. 

On the home feed users are able to view, follow, like and search from thousands of different posts. On the peer-to-peer messaging fragment users are able to individually message other users as well as being able to block others which will remove them from the messaging view. On the post fragment, users can view their own created posts as well as create new posts. When a user creates a new post, a subfragment view is displayed where individual, small group or large group posts can be selected. Each post type has differences between them and with the use of a factory method post creation is standardised. Finally, on the profile fragment users can view their profile picture and name towards the top of the screen and view their following posts below.

Fitomatic is a gateway to socialise with others while in an active environment promoting a healthy lifestyle. 

![User Profile Creation Process](/images/ReportImgAssets/profileCreationProcess.png)


**Application Use Cases and or Examples**

*[Provide use cases and examples of people using your application. Who are the target users of your application? How do the users use your application?]*

*Here is a pet training application example*

*Molly wants to inquiry about her cat, McPurr's recent troublesome behaviour*
1. *Molly notices that McPurr has been hostile since...*
2. *She makes a post about... with the tag...*
3. *Lachlan, a vet, writes a reply to Molly's post...*
4. ...
5. *Molly gives Lachlan's reply a 'tick' response*

*Here is a map navigation application example*

*Targets Users: Drivers*

* *Users can use it to navigate in order to reach the destinations.*
* *Users can learn the traffic conditions*
* ...

*Target Users: Those who want to find some good restaurants*

* *Users can find nearby restaurants and the application can give recommendations*
* ...

*List all the use cases in text descriptions or create use case diagrams. Please refer to https://www.visual-paradigm.com/guide/uml-unified-modeling-language/what-is-use-case-diagram/ for use case diagram.*

## Application UML

![ClassDiagramExample](./images/ClassDiagramExample.png)
*[Replace the above with a class diagram. You can look at how we have linked an image here as an example of how you can do it too.]*

## Application Design and Decisions

*Please give clear and concise descriptions for each subsections of this part. It would be better to list all the concrete items for each subsection and give no more than `5` concise, crucial reasons of your design. Here is an example for the subsection `Data Structures`:*

*I used the following data structures in my project:*

1. *LinkedList*

   * *Objective: It is used for storing xxxx for xxx feature.*

   * *Locations: line xxx in XXX.java, ..., etc.*

   * *Reasons:*

     * *It is more efficient than Arraylist for insertion with a time complexity O(1)*

     * *We don't need to access the item by index for this feature*

2. ...

3. ...

**Data Structures**

*[What data structures did your team utilise? Where and why?]*

**Design Patterns**

*[What design patterns did your team utilise? Where and why?]*

**Grammar(s)**

<br> *Production Rules* <br>
\<Non-Terminal> ::= \<some output>
<br>
\<Non-Terminal> ::= \<some output>

*[How do you design the grammar? What are the advantages of your designs?]*

*If there are several grammars, list them all under this section and what they relate to.*

**Tokenizer and Parsers**

*[Where do you use tokenisers and parsers? How are they built? What are the advantages of the designs?]*

**Surpise Item**

*[If you implement the surprise item, explain how your solution addresses the surprise task. What decisions do your team make in addressing the problem?]*

**Other**

*[What other design decisions have you made which you feel are relevant? Feel free to separate these into their own subheadings.]*

## Summary of Known Errors and Bugs

*[Where are the known errors and bugs? What consequences might they lead to?]*

*Here is an example:*

1. *Bug 1:*

- *A space bar (' ') in the sign in email will crash the application.*
- ...

2. *Bug 2:*
3. ...

*List all the known errors and bugs here. If we find bugs/errors that your team does not know of, it shows that your testing is not thorough.*

## Testing Summary

*[What features have you tested? What is your testing coverage?]*

*Here is an example:*

*Number of test cases: ...*

*Code coverage: ...*

*Types of tests created: ...*

*Please provide some screenshots of your testing summary, showing the achieved testing coverage. Feel free to provide further details on your tests.*

## Implemented Features

*[What features have you implemented?]*

*Here is an example:*

*Privacy*
1. *Featue 1: Users may ... . (easy)*
2. *Feature 2: A user must ... . (easy)*
3. *Feature 3: A user can only ... . (medium)*

*Firebase Integration*
1. *Feature 1: Use Firebase to implement ... . (easy)*
2. *Feature 2: Use Firebase to persist ... .(medium)*

*List all features you have completed in their separate categories with their difficulty classification. If they are features that are suggested and approved, please state this somewhere as well.*

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
