import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import model.ImageModel;
import model.ImageObject;
import model.ImageObjectRGB;
import model.Model;

import static org.junit.Assert.assertEquals;

/**
 * ModelTest class contains model tests.
 */
public class ModelTest {
  Model m;

  @Before
  public void setup() {
    ImageObject im;
    m = new ImageModel();
    float[][] r = {
            {1f, 2f, 3f},
            {4f, 5f, 6f},
            {7f, 8f, 9f}
    };
    float[][] g = {
            {2f, 4f, 6f},
            {8f, 10f, 12f},
            {14f, 16f, 18f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };
    float[][] r1 = {
            {1f, 200f, 200f},
            {200f, 5f, 6f},
            {7f, 8f, 254f}
    };
    float[][] g1 = {
            {2f, 4f, 6f},
            {8f, 10f, 12f},
            {18f, 18f, 18f}
    };
    float[][] b1 = {
            {10f, 30f, 30f},
            {30f, 30f, 30f},
            {30f, 20f, 30f}
    };
    im = new ImageObjectRGB(r, g, b, 3, 3);
    m.putImage("controller", im);
    im = new ImageObjectRGB(r1, g1, b1, 3, 3);
    m.putImage("newimg", im);
  }

  //NEW TESTS

  @Test
  public void compressTest1() {
    m.compress("controller", "comp0", 0);
    ImageObject img = m.getImage("comp0");
    float[][] r = {
            {1f, 2f, 3f},
            {4f, 5f, 6f},
            {7f, 8f, 9f}
    };
    float[][] g = {
            {2f, 4f, 6f},
            {8f, 10f, 12f},
            {14f, 16f, 18f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void compressTest2() {
    m.compress("controller", "comp100", 100);
    ImageObject img = m.getImage("comp100");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(0f, img.getR()[i][j], 0.001f);
        assertEquals(0f, img.getG()[i][j], 0.001f);
        assertEquals(0f, img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void compressTest3() {
    m.compress("controller", "comp30", 30);
    ImageObject img = m.getImage("comp30");
    float[][] r = {
            {2.25f, 2.25f, 2.625f},
            {4.5f, 4.5f, 6.375f},
            {7.125f, 7.125f, 9f}
    };
    float[][] g = {
            {2.625f, 4.125f, 5.625f},
            {8.625f, 10.125f, 11.625f},
            {13.875f, 15.375f, 18.375f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void compressTest4() {
    m.compress("controller", "comp75", 75);
    ImageObject img = m.getImage("comp75");
    float[][] r = {
            {2.8125f, 2.8125f, 2.8125f},
            {2.8125f, 2.8125f, 2.8125f},
            {2.8125f, 2.8125f, 2.8125f}
    };
    float[][] g = {
            {5.625f, 5.625f, 10.125f},
            {5.625f, 5.625f, 10.125f},
            {11.625f, 11.625f, 16.125f}
    };
    float[][] b = {
            {15f, 15f, 26.25f},
            {15f, 15f, 26.25f},
            {15f, 15f, 33.75f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void colorCorrectWithoutSplit() {
    m.colorCorrect("newimg", "cc", 100);
    ImageObject img = m.getImage("cc");
    float[][] r = {
            {0f, 82f, 82f},
            {82f, 0f, 0f},
            {0f, 0f, 136f}
    };
    float[][] g = {
            {66f, 68f, 70f},
            {72f, 74f, 76f},
            {82f, 82f, 82f}
    };
    float[][] b = {
            {62f, 82f, 82f},
            {82f, 82f, 82f},
            {82f, 72f, 82f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void colorCorrectWithSplit() {
    m.colorCorrect("newimg", "cc", 50);
    ImageObject img = m.getImage("cc");
    float[][] r = {
            {0f, 0f, 200f},
            {82f, 0f, 6f},
            {0f, 0f, 254f}
    };
    float[][] g = {
            {66f, 0f, 6f},
            {72f, 0f, 12f},
            {82f, 0f, 18f}
    };
    float[][] b = {
            {62f, 0f, 30f},
            {82f, 0f, 30f},
            {82f, 0f, 30f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {

        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void levelsAdjustWithoutSplit() {
    m.levelsAdjust("newimg", "lvl", 24, 60, 120, 100);
    ImageObject img = m.getImage("lvl");
    float[][] r = {
            {0f, 255f, 255f},
            {255f, 0f, 0f},
            {0f, 0f, 148.994f}
    };
    float[][] g = {
            {0f, 0f, 0f},
            {0f, 0f, 0f},
            {0f, 0f, 0f}
    };
    float[][] b = {
            {0f, 24.031f, 24.031f},
            {24.031f, 24.031f, 24.031f},
            {24.031f, 0f, 24.031f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        //System.out.println(img.getR()[i][j] + " " + img.getG()[i][j] + " " + img.getB()[i][j]);
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void levelsAdjustWithSplit() {
    m.levelsAdjust("newimg", "lvl", 24, 60, 120, 50);
    ImageObject img = m.getImage("lvl");
    float[][] r = {
            {0f, 0f, 200f},
            {255f, 0f, 06f},
            {0f, 0f, 254f}
    };
    float[][] g = {
            {0f, 0f, 6f},
            {0f, 0f, 12f},
            {0f, 0f, 18f}
    };
    float[][] b = {
            {0f, 0f, 30f},
            {24.031f, 0f, 30f},
            {24.031f, 0f, 30f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.001f);
        assertEquals(g[i][j], img.getG()[i][j], 0.001f);
        assertEquals(b[i][j], img.getB()[i][j], 0.001f);
      }
    }
  }

  @Test
  public void testHistogram() throws IOException {
    float[][] r = new float[16][16];
    float[][] g = new float[16][16];
    float[][] b = new float[16][16];
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        r[i][j] = 16 * i + j;
      }
    }
    ImageObject im = new ImageObjectRGB(r, g, b, 16, 16);
    m.putImage("img", im);
    r = new float[256][256];
    g = new float[256][256];
    b = new float[256][256];
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        r[i][j] = 255;
        g[i][j] = 255;
        b[i][j] = 255;
      }
    }
    for (int i = 1; i <= 16; i++) {
      for (int j = 0; j < 256; j++) {
        r[i * 16 - 1][j] = 192;
        g[i * 16 - 1][j] = 192;
        b[i * 16 - 1][j] = 192;
      }
    }
    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 256; j++) {
        r[j][i * 16] = 192;
        g[j][i * 16] = 192;
        b[j][i * 16] = 192;
      }
    }
    for (int j = 0; j < 256; j++) {
      r[0][j] = 192;
      g[0][j] = 192;
      b[0][j] = 192;
      r[j][255] = 192;
      g[j][255] = 192;
      b[j][255] = 192;
    }
    for (int i = 0; i < 256; i++) {
      if (i <= 127) {
        r[i][1] = 0;
        g[i][1] = 0;
        b[i][1] = 255;
      }
      if (i == 127) {
        r[i][2] = 0;
        g[i][2] = 0;
        b[i][2] = 255;
      }
      if (i > 127) {
        r[i][0] = 0;
        g[i][0] = 0;
        b[i][0] = 255;
        r[i][2] = 0;
        g[i][2] = 0;
        b[i][2] = 255;
      }
      if (i > 1) {
        r[255][i] = 0;
        g[255][i] = 0;
        b[255][i] = 255;
      }
      r[254][i] = 255;
      g[254][i] = 0;
      b[254][i] = 0;
    }
    m.histogram("img", "img");
    im = m.getImage("img");
    float[][] imgr = im.getR();
    float[][] imgg = im.getG();
    float[][] imgb = im.getB();
    for (int i = 0; i < 256; i++) {
      for (int j = 0; j < 256; j++) {
        assertEquals(imgr[i][j], r[i][j], 0.001f);
        assertEquals(imgg[i][j], g[i][j], 0.001f);
        assertEquals(imgb[i][j], b[i][j], 0.001f);
      }
    }
  }


  //OLDER TESTS
  @Test
  public void testcreateR() {
    m.createChannel("controller", "imgr", 'r');
    ImageObject img = m.getImage("imgr");
    float[][] r = {{1f, 2f, 3f}, {4f, 5f, 6f}, {7f, 8f, 9f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(0.0f, img.getG()[i][j], 0.0f);
        assertEquals(0.0f, img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateG() {
    m.createChannel("controller", "imgg", 'g');
    ImageObject img = m.getImage("imgg");
    float[][] g = {{2f, 4f, 6f}, {8f, 10f, 12f}, {14f, 16f, 18f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(0.0f, img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(0.0f, img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateB() {
    m.createChannel("controller", "imgb", 'b');
    ImageObject img = m.getImage("imgb");
    float[][] b = {{10f, 20f, 30f}, {10f, 20f, 30f}, {10f, 20f, 30f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(0.0f, img.getR()[i][j], 0.0f);
        assertEquals(0.0f, img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateValue() {
    m.createGreyScale("controller", "imgr", 50, 'v');
    ImageObject img = m.getImage("imgr");
    float[][] r = {{10f, 0f, 3f}, {10f, 0f, 6f}, {14f, 0f, 9f}};
    float[][] g = {{10f, 0f, 6f}, {10f, 0f, 12f}, {14f, 0f, 18f}};
    float[][] b = {{10f, 0f, 30f}, {10f, 0f, 30f}, {14f, 0f, 30f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateIntensityWithSplit() {
    m.createGreyScale("controller", "imgr", 30, 'i');
    ImageObject img = m.getImage("imgr");
    float[][] r = {{0f, 2f, 3f}, {0f, 5f, 6f}, {0f, 8f, 9f}};
    float[][] g = {{0f, 4f, 6f}, {0f, 10f, 12f}, {0f, 16f, 18f}};
    float[][] b = {{0f, 20f, 30f}, {0f, 20f, 30f}, {0f, 20f, 30f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateLumaWithSplit() {
    m.createGreyScale("controller", "imgr", 40, 'l');
    ImageObject img = m.getImage("imgr");
    float[][] r = {{2.365f, 0f, 3f}, {7.294f, 0f, 6f}, {12.223f, 0f, 9f}};
    float[][] g = {{2.365f, 0f, 6f}, {7.294f, 0f, 12f}, {12.223f, 0f, 18f}};
    float[][] b = {{2.365f, 0f, 30f}, {7.294f, 0f, 30f}, {12.223f, 0f, 30f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateValueWithSplit() {
    m.createGreyScale("controller", "imgr", 50, 'v');
    ImageObject img = m.getImage("imgr");
    float[][] r = {{10f, 0f, 3f}, {10f, 0f, 6f}, {14f, 0f, 9f}};
    float[][] g = {{10f, 0f, 6f}, {10f, 0f, 12f}, {14f, 0f, 18f}};
    float[][] b = {{10f, 0f, 30f}, {10f, 0f, 30f}, {14f, 0f, 30f}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateIntensity() {
    m.createGreyScale("controller", "imgr", 100, 'i');
    ImageObject img = m.getImage("imgr");
    float[][] r = {
            {(float) (13.0 / 3f), (float) (26.0 / 3f), 13f},
            {(float) (22.0 / 3f), (float) (35.0 / 3f), 16f},
            {(float) (31.0 / 3f), (float) (44.0 / 3f), 19f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(r[i][j], img.getG()[i][j], 0.0f);
        assertEquals(r[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testcreateLuma() {
    m.createGreyScale("controller", "imgr", 100, 'l');
    ImageObject img = m.getImage("imgr");
    float[][] r = {
            {2.365f, 4.73f, 7.095f},
            {7.294f, 9.659f, 12.024f},
            {12.223f, 14.588f, 16.953f}
    };
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(r[i][j], img.getG()[i][j], 0.0f);
        assertEquals(r[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testflipHorizontal() {
    m.flipHorizontal("controller", "imgr");
    ImageObject img = m.getImage("imgr");
    float[][] r = {
            {3f, 2f, 1f},
            {6f, 5f, 4f},
            {9f, 8f, 7f}
    };
    float[][] g = {
            {6f, 4f, 2f},
            {12f, 10f, 8f},
            {18f, 16f, 14f}
    };
    float[][] b = {
            {30f, 20f, 10f},
            {30f, 20f, 10f},
            {30f, 20f, 10f}
    };

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testflipVertical() {
    m.flipVertical("controller", "imgr");
    ImageObject img = m.getImage("imgr");
    float[][] r = {
            {7f, 8f, 9f},
            {4f, 5f, 6f},
            {1f, 2f, 3f}
    };
    float[][] g = {
            {14f, 16f, 18f},
            {8f, 10f, 12f},
            {2f, 4f, 6f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testBrighten() {
    m.brighten(10f, "controller", "imgr");
    ImageObject img = m.getImage("imgr");
    float[][] r = {
            {11f, 12f, 13f},
            {14f, 15f, 16f},
            {17f, 18f, 19f}
    };
    float[][] g = {
            {12f, 14f, 16f},
            {18f, 20f, 22f},
            {24f, 26f, 28f}
    };
    float[][] b = {
            {20f, 30f, 40f},
            {20f, 30f, 40f},
            {20f, 30f, 40f}
    };

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testDarken() {
    m.darken(10f, "controller", "imgr");
    ImageObject img = m.getImage("imgr");

    float[][] r = {
            {0f, 0f, 0f},
            {0f, 0f, 0f},
            {0f, 0f, 0f}
    };
    float[][] g = {
            {0f, 0f, 0f},
            {0f, 0f, 2f},
            {4f, 6f, 8f}
    };
    float[][] b = {
            {0f, 10f, 20f},
            {0f, 10f, 20f},
            {0f, 10f, 20f}
    };

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testRGBSplit() {
    m.rgbSplit("red", "green", "blue", "controller");
    ImageObject img1 = m.getImage("red");
    ImageObject img2 = m.getImage("green");
    ImageObject img3 = m.getImage("blue");
    float[][] r = {
            {1f, 2f, 3f},
            {4f, 5f, 6f},
            {7f, 8f, 9f}
    };
    float[][] g = {
            {2f, 4f, 6f},
            {8f, 10f, 12f},
            {14f, 16f, 18f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img1.getR()[i][j], 0.0f);
        assertEquals(0.0f, img1.getG()[i][j], 0.0f);
        assertEquals(0.0f, img1.getB()[i][j], 0.0f);

        assertEquals(0.0f, img2.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img2.getG()[i][j], 0.0f);
        assertEquals(0.0f, img2.getB()[i][j], 0.0f);

        assertEquals(0.0f, img3.getR()[i][j], 0.0f);
        assertEquals(0.0f, img3.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img3.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testRGBCombine() {
    float[][] r = {
            {1f, 2f, 3f},
            {4f, 5f, 6f},
            {7f, 8f, 9f}
    };
    float[][] g = {
            {2f, 4f, 6f},
            {8f, 10f, 12f},
            {14f, 16f, 18f}
    };
    float[][] b = {
            {10f, 20f, 30f},
            {10f, 20f, 30f},
            {10f, 20f, 30f}
    };
    ImageObject img1 = new ImageObjectRGB(r, new float[3][3], new float[3][3], 3, 3);
    ImageObject img2 = new ImageObjectRGB(new float[3][3], g, new float[3][3], 3, 3);
    ImageObject img3 = new ImageObjectRGB(new float[3][3], new float[3][3], b, 3, 3);
    m.putImage("red", img1);
    m.putImage("green", img2);
    m.putImage("blue", img3);
    m.rgbCombine("red", "green", "blue", "img2");
    ImageObject img = m.getImage("img2");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testBlurWithoutSplit() {
    float[][] r = {
            {1.3125f, 2.25f, 2.0625f},
            {3.25f, 5f, 4.25f},
            {3.5625f, 5.25f, 4.3125f}
    };
    float[][] g = {
            {2.625f, 4.5f, 4.125f},
            {6.5f, 10f, 8.5f},
            {7.125f, 10.5f, 8.625f}
    };
    float[][] b = {
            {7.5f, 15f, 15f},
            {10f, 20f, 20f},
            {7.5f, 15f, 15f}
    };
    m.blur("controller", "imgr", 100);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testBlurWithSplit() {
    float[][] r = {
            {1.3125f, 0f, 3f},
            {3.25f, 0f, 6f},
            {3.5625f, 0f, 9f}
    };
    float[][] g = {
            {2.625f, 0f, 6f},
            {6.5f, 0f, 12f},
            {7.125f, 0f, 18f}
    };
    float[][] b = {
            {7.5f, 0f, 30f},
            {10f, 0f, 30f},
            {7.5f, 0f, 30f}
    };
    m.blur("controller", "imgr", 50);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }


  @Test
  public void testSharpenWithoutSplit() {
    float[][] r = {
            {0.0f, 3.75f, 2.625f},
            {7.5f, 15f, 11.25f},
            {8.625f, 15.0f, 11.625f}
    };
    float[][] g = {
            {0.0f, 7.5f, 5.25f},
            {15.0f, 30f, 22.5f},
            {17.25f, 30f, 23.25f}
    };
    float[][] b = {
            {7.5f, 37.5f, 37.5f},
            {18.75f, 60f, 56.25f},
            {7.5f, 37.5f, 37.5f}
    };
    m.sharpen("controller", "imgr", 100);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testSharpenWithSplit() {
    float[][] r = {
            {0.0f, 0f, 3f},
            {7.5f, 0f, 6f},
            {8.625f, 0f, 9f}
    };
    float[][] g = {
            {0.0f, 0f, 6f},
            {15.0f, 0f, 12f},
            {17.25f, 0f, 18f}
    };
    float[][] b = {
            {7.5f, 0f, 30f},
            {18.75f, 0f, 30f},
            {7.5f, 0f, 30f}
    };
    m.sharpen("controller", "imgr", 50);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testSepiaWithoutSplit() {
    float[][] r = {
            {3.821f, 7.642f, 11.462999f},
            {9.614f, 13.434999f, 17.256f},
            {15.407001f, 19.228f, 23.049f}
    };
    float[][] g = {
            {3.401f, 6.802f, 10.203f},
            {8.564f, 11.964999f, 15.366f},
            {13.727f, 17.128f, 20.529f}
    };
    float[][] b = {
            {2.6499999f, 5.2999997f, 7.95f},
            {6.6699996f, 9.32f, 11.969999f},
            {10.690001f, 13.339999f, 15.99f}
    };
    m.sepia("controller", "imgr", 100);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }

  @Test
  public void testSepiaWithSplit() {
    float[][] r = {
            {3.821f, 0f, 3f},
            {9.614f, 0f, 6f},
            {15.407001f, 0f, 9f}
    };
    float[][] g = {
            {3.401f, 0f, 6f},
            {8.564f, 0f, 12f},
            {13.727f, 0f, 18f}
    };
    float[][] b = {
            {2.6499999f, 0f, 30f},
            {6.6699996f, 0f, 30f},
            {10.690001f, 0f, 30f}
    };
    m.sepia("controller", "imgr", 50);
    ImageObject img = m.getImage("imgr");
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        assertEquals(r[i][j], img.getR()[i][j], 0.0f);
        assertEquals(g[i][j], img.getG()[i][j], 0.0f);
        assertEquals(b[i][j], img.getB()[i][j], 0.0f);
      }
    }
  }
}
