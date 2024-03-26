## **Enhancement Added: Entry Class**

### Description
In the brainstorming of our application, we decided that the baseline attributes of a person is too restrictive. Since our app is targetted at gamers,
it would make no sense for certain attributes to be compulsory, such as Phone, Email, and Address. Since these classes were hardcoded, we thought we could implement a parent class to encapsulate an entry, where these restrictions were removed.

Our entry class encapsulates this, just being two strings: the category and description of the entry. Should the user still want to add a Phone, he can do this, except he would need to specify that the category is "phone" and description would be the phone number.
This allows the user to add any entry he wants, which is most useful in a gaming situation where multiple games can have many different types of attributes for each character.
### How is it implemented
Since the idea of a customisable fields required a large overhaul of the base code, to prevent large compile errors I created entry as a class and attempted to let Phone, Email, Address etc inherit from it.
This allows the code to continue working so we could test it by running addressbook.jar. For hardcoded methods such as getPhone, I gathered it into  single function requiring a string, thus letting people use get("phone") instead without breaking the functionality of the base code.
Doing this, I was able to phase out the hardcoded classes while retaining ab3 functionality.

### Why it is implemented this way
Since we wanted our addressbook app to have potential infinite entries, we had to rework Person to include an Arraylist of entries. Thus, rather than specifying all the hardcoded entries during the adding of the user,
we created an AddCategory function to add an entry to a user, which will be stored in the entryList. the entrylist will be iterated through so the updated list of entries will be displayed to the user.
This way, users can create customisable entries for the people in their addressbook, such as specifying their clan, class, or playstyle.
Getters were specified in the entry class such as to allow us to implement finding functions and sorting functions for the entries, such as to find all users with the "class" "warrior".

