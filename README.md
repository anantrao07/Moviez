# Moviez

 Read ME 

****************************************************************************************
API Key : please write the API key  in inner class NetworkCall in Class MainActivityFragment.
public final static String PERSONAL_API_KEY =“APIKEY"
The app is using MOVIEDBAPI which is providing  all the data displayed in the App
****************************************************************************************
The app has the below flow : 
MainActivityFrament> inner Class NetworkCall > DetailActivityFragment

MainActivityFragment : 
Responsible for the following :

inflate the UI with the fragment and the layouts
Make a async thread call via Asyn task class
populate the data on the view attached to the fragment via OnCreateView method 


DetailActivityFragment : 

This class is responsible to pick the data from explicit intent coming from MainActivityFragment and populate the Fragment child to Parent Activity 
