# MyCloset App
## Introdaction
MyCloset is an application that help users manage their cloths in the closet, they can add,remove and search their cloths without opening the closet and from everywhere.

## Tools
### Development platform:
 Android Studio  
### Language:
 Java  
### DataBase and cloud platform:   
 FireBase -  
 1. Authintication - Implement sign up and sign in pages with Firebase authintication.
 2. Realtime Database  - Upload and retrive items to and from the Firebase realtime database.
3. Cloud storage - Store pictures.

## Page daiagram:
</br>

![PageDiagram](https://user-images.githubusercontent.com/75904114/154689532-8d8e4d4d-03af-43e2-8ef8-67a1318e9e35.PNG)


## SignIn / SignUp Activity:
</br>
<img src =https://user-images.githubusercontent.com/75904114/154692941-c11a02db-e712-455c-9e7e-53cb4708785e.png width="200">

<img src =https://user-images.githubusercontent.com/75904114/154693171-10246da5-eca4-4637-906f-5db749abbacf.png width="200">

## Menu + RecycleView Activity:
This activity contanis a tool bar and below it a recycle view with all the item the user uploaded.  
The tool bar contanis 4 clickable items:
1. Logout button - Logout the user and return to log-In page.
2. "MyCloset" title - Refresh the menu activity.
3. Search button - direct to search page.
4. Add item button - direct to add item page.  

RecycleView cointanis card with remove button to remove it from the recycleview and from the realtime database.

</br>

<img src =https://user-images.githubusercontent.com/75904114/154693741-142703cd-8748-45dd-aedf-327468f3186a.png width="200">


## Add item Activity:
This Activity allow the user to add item and fill the data needed includ a picture of the item.  
</br>
<img src =https://user-images.githubusercontent.com/75904114/154694955-794c0e9e-3149-4333-9afa-f171f0e4ceda.png width="200">

## Search item Activity:
Let the user search for specific items. 
</br>   
<img src =https://user-images.githubusercontent.com/75904114/154695483-eca4ada0-25cc-4079-be98-5fc2eebe1bcb.png width="200">