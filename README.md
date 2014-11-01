android-PullRefreshLayout
=========================

This component like SwipeRefreshLayout, it is more beautiful than SwipeRefreshLayout.

# Demo
<p>
   <img src="https://raw.githubusercontent.com/baoyongzhang/android-PullRefreshLayout/master/demo.gif" width="320" alt="Screenshot"/>
</p>

# Usage

Use method like SwipeRefreshLayout's usage.  

Use it in your layout xml.

```xml 
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

	<!-- ListView、ScrollView、RecyclerView、Other-->
	
</com.baoyz.widget.PullRefreshLayout>

```

Get instance and use it.

```java
PullRefreshLayout layout = (PullRefreshLayout) findViewById(...);

// listen refresh event
layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
    @Override
    public void onRefresh() {
        // start refresh
    }
});

// refresh complete
layout.setRefreshing(false);

```

Change the refresh type, there are two types of use, `CIRCLES` and `WATER_DROP`.  

In java, call `setRefreshType` method.

```java
layout.setRefreshType(PullRefreshLayout.TYPE_CIRCLES);
layout.setRefreshType(PullRefreshLayout.TYPE_WATER_DROP);

```

In xml, use attributes.

```xml
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
	app:type="water_drop">
	
</com.baoyz.widget.PullRefreshLayout>

```
 
Change the color scheme.
In java, call `setColorSchemeColors` method. The int array length must be 4.

```java
layout.setColorSchemeColors(int []);

```

In xml, use attributes.

```xml
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:colors="@array/scheme_colors">
	
</com.baoyz.widget.PullRefreshLayout>

```