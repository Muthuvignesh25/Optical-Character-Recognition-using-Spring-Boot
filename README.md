<h2>Introduction:</h2>
<ul style="list-style-type:square;">
<li>Optical Character Recognition (OCR) was devised as a way to allow computers to "read" graphical content as text, similar to how humans do. Of course these systems, while relatively accurate, can still be off quite a bit. Even if they are, fixing up the mistakes of the system is still a lot easier and faster than doing everything from scratch by hand.</li>
<li>Like all systems, similar-in-nature, optical character recognition software trains on prepared datasets that feed it enough data to learn the difference between characters.</li>
<li>Instead of reinventing the wheel and coming up with a very complex (but useful) solution, let's settle down a bit and use what's already offered.</li></p>
</ul>

<h2>Tesseract:</h2>
<ul style="list-style-type:square;">
<p><li>The technology giant, Google, has been developing an OCR engine, Tesseract, which has a decades-long history since its original inception. It offers an API for a bunch of languages, though we'll focus on the Tesseract Java API.</li>

<li>Tesseract is very easy to implement, and subsequently isn't overly powerful. It's mainly used for reading computer generated text on black and white images, which is done with decent accuracy. Although it's not really meant for real-world text.</li>

<li>For real-world, advanced Optical Character Recognition, we'd be better off using something like Google Vision, which we'll go over in another article.</li></p>
            </ul>

<h2>Maven Dependency</h2>
To import the engine into our project, we simply have to add a dependency:

![image](https://user-images.githubusercontent.com/101090514/174450096-0fced6af-b285-440e-82f6-293784f051f1.png)
<h2>Optical Character Recognition:</h2>
Using Tesseract is absolutely effortless:

![image](https://user-images.githubusercontent.com/101090514/174450125-8c4bf577-3682-4a76-8824-63e755ee712d.png)

We firstly instantiate the Tesseract object and set the data path to the LSTM (Long Short-Term Memory) models pre-trained for your use.The data can be downloaded from the official GitHub account.

Afterwards, we call the doOCR() method, which accepts a file and returns a String - the extracted content.


Let's feed it a picture with big, clear black letters upon a white background:
![image](https://user-images.githubusercontent.com/101090514/174449855-bb349742-38fb-4420-98da-30e765592e99.png)


Feeding it such a picture will produce a perfect result:
![image](https://user-images.githubusercontent.com/101090514/174449880-4fa591c1-9996-4e8f-b21c-167fe0d46d9f.png)

Optical Character Recognition in Java is made easy with the help of Tesseract.

Now, to make this a bit easier to use, let's transfer it into a very simple Spring Boot application to serve up the result in a more graphically pleasing way.

First off, let's start by generating our project through Spring Initializr. Include the spring-boot-starter-web and spring-boot-starter-thymeleaf dependencies. We'll import Tesseract manually.</p>


<h2>Controller:</h2>
<ul style="list-style-type:square;">
<p><li>The app doesn't need more than a single controller, which serves up our two views and handles the picture upload and optical character recognition.</li>



<li>Tesseract works with Java's Files, but doesn't support MultipartFile, which we get by accepting a file through our form. To mitigate this, we've added a simple convert() method, which converts the MultipartFile into a regular File.</p></li>
</ul>

Once we've extracted the text using Tesseract, we simply add it to the model, alongside the scanned image and append it to the redirected view - result.</p>

<h2>Views:</h2>
<p>Now, let's define a view which we can use to simply upload a file via a form:
Running this app will greet us with a plain interface:</p>

![image](https://user-images.githubusercontent.com/101090514/174449520-c9dd3140-1b3c-4411-b5fe-4b82e69e06e6.png)
<p>Adding an image and submitting it will result in the text being extracted and the image shown on the screen And the resulting page:</p>
<img src="https://user-images.githubusercontent.com/101090514/174450271-a3274cb9-a82c-4f10-834b-1b5da526837b.png" width="900" height="600" >




<h2>Conclusion:</h2>
<ul style="list-style-type:square;">
<p><li>Using Google's Tesseract engine, we built an extremely simple app that accepts an image through a form, extracts the textual contents from it and returns to us the submitted image.</li>

<li>Optical Character Recognition can come in handy when you wish to digitize content, especially when it comes to documents. These are easy to scan and are fairly accurate when it comes to extracting content. Of course, it's always wise to proofread the resulting document for potential errors.</li></p>
            </ul>
