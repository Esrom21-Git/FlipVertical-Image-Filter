// Test class to demonstrate the functionality
public class ImageFilterTest {

    public static void main(String[] args) {
        // Create a test image with a gradient pattern
        System.out.println("=== Testing FlipVertical Filter ===");

        // Create a simple 4x4 test image
        PixelImage testImage = createTestImage();

        System.out.println("Original Image:");
        testImage.display();

        // Apply the flip vertical filter
        FlipVertical flipFilter = new FlipVertical();
        flipFilter.filter(testImage);

        System.out.println("\nFlipped Image:");
        testImage.display();

        // Test with the copy version
        System.out.println("\n=== Testing FlipVerticalCopy Filter ===");
        PixelImage testImage2 = createTestImage();

        System.out.println("Original Image:");
        testImage2.display();

        FlipVerticalCopy flipCopyFilter = new FlipVerticalCopy();
        flipCopyFilter.filter(testImage2);

        System.out.println("Flipped Image (Copy method):");
        testImage2.display();

        // Test edge cases
        System.out.println("\n=== Testing Edge Cases ===");
        testEdgeCases();
    }

    private static PixelImage createTestImage() {
        // Create a 4x6 image with different colors in each row
        PixelImage image = new PixelImage(6, 4);

        // Row 0: Red gradient
        for (int col = 0; col < 6; col++) {
            image.setPixel(0, col, new Pixel(255 - col * 40, 0, 0));
        }

        // Row 1: Green gradient
        for (int col = 0; col < 6; col++) {
            image.setPixel(1, col, new Pixel(0, 255 - col * 40, 0));
        }

        // Row 2: Blue gradient
        for (int col = 0; col < 6; col++) {
            image.setPixel(2, col, new Pixel(0, 0, 255 - col * 40));
        }

        // Row 3: White to black gradient
        for (int col = 0; col < 6; col++) {
            int intensity = 255 - col * 50;
            image.setPixel(3, col, new Pixel(intensity, intensity, intensity));
        }

        return image;
    }

    private static void testEdgeCases() {
        FlipVertical flipFilter = new FlipVertical();

        // Test null image
        System.out.println("Testing null image:");
        flipFilter.filter(null);

        // Test 1x1 image
        System.out.println("Testing 1x1 image:");
        PixelImage tiny = new PixelImage(1, 1);
        tiny.setPixel(0, 0, new Pixel(255, 128, 64));
        tiny.display();
        flipFilter.filter(tiny);
        tiny.display();

        // Test single row image
        System.out.println("Testing single row image:");
        PixelImage singleRow = new PixelImage(5, 1);
        for (int col = 0; col < 5; col++) {
            singleRow.setPixel(0, col, new Pixel(col * 60, col * 60, col * 60));
        }
        singleRow.display();
        flipFilter.filter(singleRow);
        singleRow.display();

        System.out.println("Edge case testing complete!");
    }
}
