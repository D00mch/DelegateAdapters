# DelegateAdapters
Simplify creating recycler view adapters with different view types

## Dependencies

```groovy
compile 'com.github.liverm0r:delegateadapters:v1.05'
```

You also have to add this in your project build.gradle

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

[![Build Status](https://travis-ci.org/sockeqwe/AdapterDelegates.svg?branch=master)](https://jitpack.io/#Liverm0r/delegateadapters)


## Example of usage in Java

Inherit from BaseDelegateAdapter:

```java
public class TextDelegateAdapter
    extends BaseDelegateAdapter<TextDelegateAdapter.TextViewHolder, TextViewModel> {


    @Override
    protected void onInflated(@NonNull View view,
                              @NonNull TextViewModel item,
                              @NonNull TextViewHolder viewHolder) {
        viewHolder.tvTitle.setText(item.title);
        viewHolder.tvDescription.setText(item.description);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.text_item;
    }

    @Override
    protected TextViewHolder createViewHolder(View parent) {
        return new TextViewHolder(parent);
    }

    @Override
    public boolean isForViewType(@NonNull List<Object> items, int position) {
        return items.get(position) instanceof TextViewModel;
    }


    final class TextViewHolder extends BaseViewHolder {

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

Use it like base RecyclerView.Adapter:

```java

    CompositeDelegateAdapter adapter = new CompositeDelegateAdapter.Builder()
        .add(new ImageDelegateAdapter(onImageClick))
        .add(new TextDelegateAdapter())
        .build();
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);
```

  ## License

```
Copyright 2015 Hannes Dorfmann

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
