### MyNotes
This is the project was implemented to learn and understand how CRUD Operation in Room Database works \
The Room library provides an abstraction layer over SQLite \
While working on this project i decided to implement **MVVM architecture** in this project \
**The Model-View-ViewModel (MVVM)** pattern helps to cleanly separate the back-end logic of an application from its user interface (UI) \
The topics that i learnt from this project are : \
**Room** - it makes data storing and managing easier over SQLite  \
**View Binding** -  replaces the findViewById() method :)  \
**AsyncTask** -  runs on a background thread has 4 methods -> doInBackground() | onPreExecute() | onProgressUpdate() | onPostExecute()  \
**Threads** - used to run a task on a background thread \
**Executor** - used instead of explicitly creating threads \
**Runnable** - are the instances are intended to be executed by a thread \
**Handler** - allows you to send and process message  \
**ListAdapter** - ListAdapter is a subclass of the normal RecyclerView adapter, that implements AsyncListDiffer to calculate the differences between the old data set and the new one we get passed in the LiveData’s onChanged method, so we don’t have to rely on notifyDataSetChanged to update our adapter 
**LiveData & MutableLiveData** - In LiveData we can only observe the data and cannot set the data \
MutableLiveData is mutable and is a subclass of LiveData \
In MutableLiveData we can observe and set the values using postValue() and setValue() methods \



https://user-images.githubusercontent.com/107514813/177778871-e0c33c9e-3480-4104-9690-0baa2413a1b7.mp4

![Screenshot_20220707-181149](https://user-images.githubusercontent.com/107514813/177779244-2dbb4971-ad12-4add-88fb-c122d85a546a.png)
![Screenshot_20220707-181159](https://user-images.githubusercontent.com/107514813/177779254-3c975a23-4a86-44ed-b37b-9ba297dd5202.png)
![Screenshot_20220707-181232](https://user-images.githubusercontent.com/107514813/177779256-41230e62-f295-4e1f-8e51-9af44d661477.png)
![Screenshot_20220707-181339](https://user-images.githubusercontent.com/107514813/177779257-fae0c48e-c5de-4848-b661-e5ea28571af6.png)
![Screenshot_20220707-181346](https://user-images.githubusercontent.com/107514813/177779261-61b5ecbf-93ad-4425-b435-a7de8dc9fbfa.png)
