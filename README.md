# FlipVertical Image Filter

A Java implementation of a vertical image flipping filter that transforms images by reversing the order of pixel rows (top-to-bottom flip).

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Quick Start](#quick-start)
- [Classes Documentation](#classes-documentation)
- [Usage Examples](#usage-examples)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Implementation Details](#implementation-details)
- [Common Issues](#common-issues)
- [Contributing](#contributing)

## 🎯 Overview

The FlipVertical filter takes an image and flips it vertically by swapping pixel rows from top and bottom, working toward the center. The first row becomes the last row, the second row becomes the second-to-last row, and so on.

**Before:**
```
Row 0: [Red pixels]
Row 1: [Green pixels] 
Row 2: [Blue pixels]
Row 3: [White pixels]
```

**After:**
```
Row 0: [White pixels]
Row 1: [Blue pixels]
Row 2: [Green pixels] 
Row 3: [Red pixels]
```

## ✨ Features

- **Efficient in-place transformation** - Modifies the original image data directly
- **Comprehensive error handling** - Safely handles null images and invalid data
- **Edge case support** - Works with 1x1 images, single rows, and various dimensions
- **Two implementation approaches** - In-place modification and array copying
- **Full test suite** - Includes visual verification and edge case testing
- **Memory efficient** - O(1) space complexity for the main implementation

## 🚀 Quick Start

### Basic Usage

```java
// Create or load your image
PixelImage myImage = new PixelImage(width, height);

// Create the filter
FlipVertical flipFilter = new FlipVertical();

// Apply the filter
flipFilter.filter(myImage);

// Your image is now flipped vertically!
```

### Complete Example

```java
public class Example {
    public static void main(String[] args) {
        // Create a simple test image
        PixelImage image = new PixelImage(4, 3);
        
        // Set some pixels for visualization
        image.setPixel(0, 0, new Pixel(255, 0, 0));    // Red top-left
        image.setPixel(2, 3, new Pixel(0, 255, 0));    // Green bottom-right
        
        System.out.println("Before flip:");
        image.display();
        
        // Apply the flip
        FlipVertical flipFilter = new FlipVertical();
        flipFilter.filter(image);
        
        System.out.println("After flip:");
        image.display();
    }
}
```

## 📚 Classes Documentation

### `Filter` Interface
```java
public interface Filter {
    void filter(PixelImage theImage);
}
```
Base interface that all image filters must implement.

### `Pixel` Class
Represents an individual RGB pixel with values from 0-255.

**Constructor:**
- `Pixel(int red, int green, int blue)` - Creates a pixel with RGB values (auto-clamped to 0-255)

**Key Methods:**
- `getRed()`, `getGreen()`, `getBlue()` - Get color component values
- `setRed(int)`, `setGreen(int)`, `setBlue(int)` - Set color components
- `toString()` - Returns formatted string like "RGB(255,128,64)"

### `PixelImage` Class
Represents a 2D image composed of Pixel objects.

**Constructors:**
- `PixelImage(int width, int height)` - Creates blank black image
- `PixelImage(Pixel[][] pixelData)` - Creates image from pixel array

**Key Methods:**
- `getWidth()`, `getHeight()` - Get image dimensions
- `getData()` - Get the 2D pixel array
- `getPixel(int row, int col)` - Get specific pixel
- `setPixel(int row, int col, Pixel pixel)` - Set specific pixel
- `display()` - Print image to console for debugging
- `copy()` - Create deep copy of the image

### `FlipVertical` Class
The main filter implementation.

**Method:**
- `filter(PixelImage theImage)` - Flips the image vertically

**Features:**
- Null-safe operation
- In-place transformation for memory efficiency
- Handles all image sizes including edge cases

### `FlipVerticalCopy` Class
Alternative implementation that creates a new array.

**Use when:**
- You want to preserve the original image
- Working with immutable image requirements
- Need to avoid modifying source data

## 💡 Usage Examples

### Example 1: Basic Image Flipping
```java
PixelImage photo = loadImageFromFile("photo.jpg");
FlipVertical flipper = new FlipVertical();
flipper.filter(photo);
saveImageToFile(photo, "photo_flipped.jpg");
```

### Example 2: Processing Multiple Images
```java
FlipVertical flipper = new FlipVertical();
String[] imageFiles = {"img1.jpg", "img2.png", "img3.gif"};

for (String filename : imageFiles) {
    PixelImage img = loadImage(filename);
    flipper.filter(img);
    saveImage(img, "flipped_" + filename);
}
```

### Example 3: Using the Copy Version
```java
PixelImage original = loadImage("original.jpg");
PixelImage flipped = original.copy();

FlipVerticalCopy copyFlipper = new FlipVerticalCopy();
copyFlipper.filter(flipped);

// Now you have both original and flipped versions
```

## ⚠️ Error Handling

The filter includes robust error handling:

| Scenario | Behavior |
|----------|----------|
| Null image | Prints error message, returns safely |
| Null pixel data | Prints error message, returns safely |
| Invalid dimensions | Prints error message, returns safely |
| Valid edge cases | Processes normally (1x1, single row, etc.) |

### Error Messages
- `"Error: Cannot flip null image"` - Input image is null
- `"Error: Image data is null"` - Image exists but pixel data is null
- `"Error: Invalid image dimensions"` - Width or height is ≤ 0

## 🧪 Testing

### Running Tests
```java
public static void main(String[] args) {
    ImageFilterTest.main(args);
}
```

### Test Coverage
- ✅ Normal image flipping (4x6 gradient image)
- ✅ Null image handling
- ✅ Single pixel image (1x1)
- ✅ Single row image
- ✅ Visual verification with colored gradients
- ✅ Both filter implementations

### Sample Test Output
```
=== Testing FlipVertical Filter ===
Original Image:
Image 6x4:
(255,  0,  0) (215,  0,  0) (175,  0,  0) ...
(  0,255,  0) (  0,215,  0) (  0,175,  0) ...
...

Flipped Image:
Image 6x4:
(255,255,255) (205,205,205) (155,155,155) ...
(  0,  0,255) (  0,  0,215) (  0,  0,175) ...
...
```

## 🔧 Implementation Details

### Algorithm Complexity
- **Time Complexity:** O(width × height / 2)
- **Space Complexity:** O(1) for in-place version, O(width × height) for copy version

### Memory Usage
The in-place implementation is highly memory efficient as it only swaps existing pixels without creating new arrays.

### Thread Safety
The filter is **not** thread-safe. If you need to process the same image from multiple threads, use the copy version or add synchronization.

## 🐛 Common Issues

### Issue: "setData() method not found"
**Solution:** Use the main `FlipVertical` class which doesn't call `setData()`. The filter modifies the pixel array directly.

