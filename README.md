android-PullRefreshLayout
=========================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-android--PullRefreshLayout-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1084)

This component like SwipeRefreshLayout, it is more beautiful than SwipeRefreshLayout.

# Demo
<p>
   <img src="https://raw.githubusercontent.com/baoyongzhang/android-PullRefreshLayout/master/demo.gif" width="320" alt="Screenshot"/>
</p>

# Usage

Add dependency.

```
dependencies {
    compile 'com.baoyz.pullrefreshlayout:library:1.2.0'
}
```

Use method like SwipeRefreshLayout's usage.  

Use it in your layout xml.

```xml 
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

	<!-- ListView、ScrollView、RecyclerView、Other -->
	
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

Change the refresh style, there are five styles of use, `MATERIAL`、`CIRCLES`、 `WATER_DROP`、`RING` and `SMARTISAN`.  

In java, call `setRefreshStyle` method.

```java
layout.setRefreshStyle(PullRefreshLayout.STYLE_CIRCLES);

```

In xml, use attributes.

```xml
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
	app:refreshType="water_drop">
	
</com.baoyz.widget.PullRefreshLayout>

```
 
Change the color scheme.
In java, call `setColorSchemeColors` method. The int array length must be 4.

```java
layout.setColorSchemeColors(int []);

```

For Smartisan style, it has only one color, can call 'setColor' method, to set one color.

```java
layout.setColor(int);
```

In xml, use attributes.

```xml
<com.baoyz.widget.PullRefreshLayout
    android:id="@+id/swipeRefreshLayout"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:refreshColors="@array/scheme_colors"
    app:refreshColor="@color/one_color">
	
</com.baoyz.widget.PullRefreshLayout>

```

If you do not like these styles, you can customize the refresh style.

```java
class CustomDrawable extends RefreshDrawable{

    @Override
    public void setPercent(float percent) {
        // Percentage of the maximum distance of the drop-down refresh.
    }

    @Override
    public void setColorSchemeColors(int[] colorSchemeColors) {
        
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        // Drop-down offset.
    }

    @Override
    public void start() {
        isRunning = true;
        // Refresh started, start refresh animation.
    }

    @Override
    public void stop() {
        isRunning = false;
        // Refresh completed, stop refresh animation.
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void draw(Canvas canvas) {
        // Draw custom style.
    }

}

```

Call `setRefreshDrawable()` method to use your custom refresh drawable.

```java
layout.setRefreshDrawable(new CustomDrawable());
```

# Thanks

* [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html)
* [GoogleProgressBar](https://github.com/jpardogo/GoogleProgressBar) 

License
=======

    The MIT License (MIT)

	Copyright (c) 2014 baoyongzhang

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.


