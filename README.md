# DelegateAdapters
Simplify creating recycler view adapters with many different view types.
This lib is inspired by Hannes Dorfmann [AdapterDelegates](https://github.com/sockeqwe/AdapterDelegates).

[Article](https://habr.com/post/341738/) about it on Russian.

## Dependencies

for java:
```groovy
implementation 'com.github.liverm0r:delegateadapters:v1.11'
```

for kotlin:
```groovy
android {
    //...
    androidExtensions {
        experimental = true
    }
}
implementation 'com.github.liverm0r:delegateadapters:v2.11'
```

You also have to add this in your project build.gradle

```groovy
allprojects {
    repositories {
        //...
        maven { url 'https://jitpack.io' }
    }
}
```

[![Build Status](https://travis-ci.org/sockeqwe/AdapterDelegates.svg?branch=master)](https://jitpack.io/#Liverm0r/delegateadapters)

## Example of usage in Kotlin

Write a model, which represents ui data:

```kotlin
data class ImageItem(val title: String, @DrawableRes val imageRes: Int) : KDiffUtilItem {
    override val id: Any = title
}
```

ImageItem is just a POJO, implementing DiffUtilItem to be able to animate it out of the box with DiffUtils.

Write a delegate adapter:

```kotlin
class ImageDelegateAdapter(private val clickListener: View.OnClickListener) : KDelegateAdapter<ImageItem>() {

    override fun KViewHolder.onBind(item: ImageItem) {
        tv_title.text = item.title
        img_bg.setOnClickListener(clickListener)
        img_bg.setImageResource(item.imageRes)
    }

    override fun isForViewType(item: Any) = item is ImageItem
    override fun getLayoutId(): Int = R.layout.image_item
}
```

Check `KViewHolder.onBind` part. This works like the basic view holder without creating one. Just override onBind and onCreate methods. See the [View holder pattern support and caching options](
https://github.com/Kotlin/KEEP/blob/master/proposals/android-extensions-entity-caching.md
) for more information.

To enable this magic, you need to turn on kotlin ext. experimental feature in your module build.gradle file:

```groovy
androidExtensions {
    experimental = true
}
```

Now you can use DiffUtilCompositeAdapter just like the base RecyclerView.Adapter, composing it with whatever amount of delegate adapters:

```kotlin
        adapter = DiffUtilCompositeAdapter.Builder()
                .add(ImageDelegateAdapter(onImageClick))
                .add(TextDelegateAdapter())
                .add(CheckDelegateAdapter())
                .build()
```

![example](https://github.com/Liverm0r/DelegateAdapters/blob/master/feed_example.jpg)

See example in code: [KotlinBaseExampleActivity.kt][1]

[1]: https://github.com/Liverm0r/DelegateAdapters/blob/master/example/src/main/java/com/example/dumchev/delegateadapters/base/KotlinBaseExampleActivity.kt

## Example of usage in Java

Write a Model:

```java
public class TextItem implements IComparableItem {

    @NonNull public final String title;
    @NonNull public final String description;

    public TextItem(@NonNull String title, @NonNull String description) {
        this.title = title;
        this.description = description;
    }

    @Override public Object id() {return title;}
    @Override public Object content() {return title + description;}
}

```

Inherit from BaseDelegateAdapter:

```java
public class TextDelegateAdapter extends
    BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextItem> {

    @Override
    protected void onBindViewHolder(@NonNull View view,
                                    @NonNull TextItem item,
                                    @NonNull TextViewHolder viewHolder) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDescription.setText(item.description);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_item;
    }

    @NonNull
    @Override
    protected TextViewHolder createViewHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<?> items, int position) {
        return items.get(position) instanceof TextItem;
    }

    final static class TextViewHolder extends BaseViewHolder {

        private TextView tvTitle;
        private TextView tvDescription;

        private TextViewHolder(View parent) {
            super(parent);
            tvTitle = parent.findViewById(R.id.tv_title);
            tvDescription = parent.findViewById(R.id.tv_description);
        }
    }
}

```

And create your CompositeDelegateAdapter (RecyclerView.Adapter):

```java
        adapter = new DiffUtilCompositeAdapter.Builder()
            .add(new ImageDelegateAdapter(onImageClick))
            .add(new TextDelegateAdapter())
            .add(new CheckDelegateAdapter())
            .build();
```


See code-example: [BaseExampleActivity.java][2] 

[2]: https://github.com/Liverm0r/DelegateAdapters/blob/master/example/src/main/java/com/example/dumchev/delegateadapters/base/BaseExampleActivity.java

  ## License

```
Copyright 2017 Artur Dumchev 

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
