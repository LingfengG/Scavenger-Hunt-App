package org.tensorflow.lite.examples.detection;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class ItemList
{
    private static HashMap<String, Boolean> itemList;

    public ItemList()
    {
        if (itemList == null) {
            itemList = new HashMap<String, Boolean>();
            itemList.put("apple", false);
            itemList.put("orange", false);
            itemList.put("banana", false);
        }
    }

    public static HashMap<String, Boolean> getItemList()
    {
        return itemList;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean findItem(String s)
    {
        boolean previous = true;
        try {
            if (itemList.containsKey(s)) {
                previous = itemList.get(s);
                itemList.replace(s, previous, true);
            }
        } catch (NullPointerException ignored) {}
        return !previous;
    }

    // gets all items that have the same found status as the parameter
    public String getItemsWith(boolean foundStatus)
    {
        StringBuilder list_builder = new StringBuilder();
        Set<String> items = itemList.keySet();

        for(String item: items)
        {
            boolean found = itemList.get(item);
            if (foundStatus == found) {
                list_builder.append(item);
                list_builder.append("\n");
            }
        }

        return list_builder.toString();
    }

    @SuppressWarnings("ConstantConditions")
    @NonNull
    public String toString()
    {
        StringBuilder list_builder = new StringBuilder();
        Set<String> items = itemList.keySet();

        for(String item: items)
        {
            list_builder.append(item);
            boolean found = itemList.get(item);
            if (found) list_builder.append(" ✔");
            list_builder.append("\n");
        }

        return list_builder.toString();
    }

    public boolean allFound()
    {
        boolean foundStatus = true;
        Collection<Boolean> foundStatuses = itemList.values();
        for (boolean found : foundStatuses)
        {
            if (!found)
            {
                foundStatus = false;
                break;
            }
        }
        return foundStatus;
    }

    public void randomize()
    {
        String[] classNames = {"person", "bicycle", "car", "motorcycle", "airplane",
            "bus", "train", "truck", "boat", "traffic light",
            "fire hydrant", "stop sign", "parking meter", "bench", "bird",
            "cat", "dog", "horse", "sheep", "cow", "elephant", "bear",
            "zebra", "giraffe", "backpack", "umbrella", "handbag", "tie",
            "suitcase", "frisbee", "skis", "snowboard", "sports ball",
            "kite", "baseball bat", "baseball glove", "skateboard",
            "surfboard", "tennis racket", "bottle", "wine glass", "cup",
            "fork", "knife", "spoon", "bowl", "banana", "apple",
            "sandwich", "orange", "broccoli", "carrot", "hot dog", "pizza",
            "donut", "cake", "chair", "couch", "potted plant", "bed",
            "dining table", "toilet", "tv", "laptop", "mouse", "remote",
            "keyboard", "cell phone", "microwave", "oven", "toaster",
            "sink", "refrigerator", "book", "clock", "vase", "scissors",
            "teddy bear", "hair drier", "toothbrush"};

        itemList = new HashMap<String, Boolean>();

        int items = (int) (4.0*Math.random() + 3);

        int len = classNames.length;

        for (int i = 0; i < items; i++)
        {
            int randIndex = (int) (len*Math.random());
            String itemName = classNames[randIndex];
            itemList.put(itemName, false);
        }
    }
}
