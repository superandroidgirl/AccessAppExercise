# AccessAppExercise

## Feature
 * Environment
- Android Studio 3.5
- Compile SDK Version 29
- Kotlin 1.3.50
 * Page introduction
- User list page
	- item
		- user image
		- user login(name)
		- staff or not
	- Paginated 
	- Start with since=0, page size = 20
	- Limit to 100 users
	- Avoid to click double item
- User detail page
	- item
		- user image
		- user name
		- user bio
		- user login (name)
		- staff or not
		- location
		- blog
	- when click blog link will open outside webview

- Architecture
	- MVVM
	- Model
		- UserInfo (user lists data)
		- UserDetail (user detail info)
	- ViewModel
		- MainViewModel
			- loadUserList 		(get first users array)
			- loadMoreUserList	(get more users array)
			- loadUserDetail	(get user's info)
		- UserRepository	(make a api service)
		- Api 				(each api path and parameters)

	- View
		- MainActivity			(User list page)
		- UserListAdapter		(Users adapter)
		- UserDetailActivity	(User detail page)
## Third Party
- Retrofit2
- Glide
- Parceler
