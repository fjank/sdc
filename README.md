# Suppression via Disc Covering
Efficiently selecting spatially distributed keypoints for visual tracking.
This implementation is based on information from [this paper](http://lucafoschini.com/papers/Efficiently_ICIP11.pdf) by
Steffen Gauglitz, Luca Foschini, Matthew Turk and Tobias Hollerer.
IEEE International Conference on Image Processing (ICIP 2011).

It is originally designed to improve the set of keypoints after doing an initial keypoint detection from [OpenCV](https://opencv.org).
[Read more about Keypoint detection.](https://docs.opencv.org/3.4.1/db/d27/tutorial_py_table_of_contents_feature2d.html)
Since this is a Java library and not C/C++, it's main use is from [JavaCV](https://github.com/bytedeco/javacv)
or the [OpenCV Android platform](https://opencv.org/platforms/android/). That said, there is no reason
this library cannot be used in other circumstances or from other libraries, as there is no external dependencies.

## Getting started

### Setting up the dependency
Include sdc as a dependency into your project, for example as a Gradle compile time dependency:
```
compile "io.github.fjank:sdc:1.0.0"
```

### Integrating into your project

Example of usage where you already have a list of keypoints, and need to improve the result:
```java
List<KeyPoint> list = getKeyPoints(); 
/* 
 * Store this filter in an instance variable for optimal performance. 
 * You could also store one filter for each radius you are going through 
 * for even better performance.
 */
KeyPointFilter filter = new KeyPointFilter(imageWidth, imageHeight);

// if the items have a response, do sort them on the response first, to avoid filtering out points with good response.
filter.sortPointsByResponse(list);

/* 
 * The initial radius needs to be figured out by trial and error. 
 * Start with the radius that gives you the closest result to 1 500 (in this example).
 */ 
int radius = 10;

// If you need 1500 good points, execute the filter, adjusting the radius until we reach about 1500 points.
List<KeyPoint> returnValue = new ArrayList<>();
while (returnValue.size() < 1500) {
    returnValue = filter.filterByRadius(radius, list);
    radius -= 2;
    // Did not get the desired keypoints, so we can stop now.
    if (radius == 0) {
        break;
    }
}

/* 
 * Now the returnValue should contain approximately 1500 points that are spatially well distributed, 
 * and this list can be used for following procedures.
 */

```

## Performance
Since this library is designed to come after Keypoint detection, before sending it to another algorithm for further processing,
it's important that the filtering is executed as fast as possible.

The current implementation do not use multiple threads, do not use approximation for the discs, finally do not downsample the matrix.
There are custom optimizations for circle from radius 1-10, so disc covering operations with these radius's should perform faster.

Below are some benchmark numbers for my computer (JMH Benchmark is included, so you can try yourself on your computer to compare results):
* CPU: Intel Core i7-2720QM 2.60 GHz.
* Java: JDK 1.8.0_91, VM 25.91-b14 server VM.
* Threads: 1
* Forks: 1
* Warmup: 10 x 1s
* Execution: 10 x 1s
* Number of points: 12 000
* Results are given in throughput (ops/sec)

| Matrix size | Radius 5 | Radius 10 | Radius 15 | Radius 20 |
|-------------|---------:|----------:|----------:|----------:|
| 512x512     | ~2 250   | ~3 000    | ~2 750    | ~3 350    |
| 1024x1024   | ~1 050   | ~1 000    | ~900      | ~1 000    |
| 2048x2048   | ~540     | ~390      | ~310      | ~320      |
| 4096x4096   | ~235     | ~170      | ~130      | ~115      |

## Build
To build the project yourself:
```
$ git clone https://github.com/fjank/sdc.git
$ cd sdc
$ gradlew build
```

## License
```
MIT License

Copyright (c) 2018 Frank Karlstrøm

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
```