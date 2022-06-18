<h3>Introduction:</h3>
               <p> Developing symbols which have some value is a trait unique to humans. Recognizing these symbols and understanding the letters on an image is absolutely normal for us. We never really grasp letters like computers do, we completely base our ability to read them on our sight.

On the other hand, computers need something more concrete and organized to work with. They need a digital representation, not a graphical one.

Sometimes, this simply isn't possible. Sometimes, we wish to automate a task of rewriting text from an image with our own hands.

For these tasks, Optical Character Recognition (OCR) was devised as a way to allow computers to "read" graphical content as text, similar to how humans do. Of course these systems, while relatively accurate, can still be off quite a bit. Even if they are, fixing up the mistakes of the system is still a lot easier and faster than doing everything from scratch by hand.

Like all systems, similar-in-nature, optical character recognition software trains on prepared datasets that feed it enough data to learn the difference between characters. It's also very important how these networks learn, if we want to make them accurate, though this is a topic for another article.


Instead of reinventing the wheel and coming up with a very complex (but useful) solution, let's settle down a bit and use what's already offered.</p>

<h3>Tesseract:</h3>
The technology giant, Google, has been developing an OCR engine, Tesseract, which has a decades-long history since its original inception. It offers an API for a bunch of languages, though we'll focus on the Tesseract Java API.

Tesseract is very easy to implement, and subsequently isn't overly powerful. It's mainly used for reading computer generated text on black and white images, which is done with decent accuracy. Although it's not really meant for real-world text.

For real-world, advanced Optical Character Recognition, we'd be better off using something like Google Vision, which we'll go over in another article.

Maven Dependency
To import the engine into our project, we simply have to add a dependency:

<dependency>
    <groupId>net.sourceforge.tess4j</groupId>
    <artifactId>tess4j</artifactId>
    <version>4.5.2</version>
</dependency>
Optical Character Recognition
Using Tesseract is absolutely effortless:

Tesseract tesseract = new Tesseract();
tesseract.setDatapath("E://DataScience//tessdata");
System.out.println(tesseract.doOCR(new File("...")));

We firstly instantiate the Tesseract object and set the data path to the LSTM (Long Short-Term Memory) models pre-trained for your use.

The data can be downloaded from the official GitHub account.

Afterwards, we call the doOCR() method, which accepts a file and returns a String - the extracted content.


Let's feed it a picture with big, clear black letters upon a white background:
![image](https://user-images.githubusercontent.com/101090514/174449855-bb349742-38fb-4420-98da-30e765592e99.png)


Feeding it such a picture will produce a perfect result:
![image](https://user-images.githubusercontent.com/101090514/174449880-4fa591c1-9996-4e8f-b21c-167fe0d46d9f.png)

Optical Character Recognition in Java is made easy with the help of Tesseract.

Now, to make this a bit easier to use, let's transfer it into a very simple Spring Boot application to serve up the result in a more graphically pleasing way.

First off, let's start by generating our project through Spring Initializr. Include the spring-boot-starter-web and spring-boot-starter-thymeleaf dependencies. We'll import Tesseract manually.


<h3>Controller:</h3>
<p>The app doesn't need more than a single controller, which serves up our two views and handles the picture upload and optical character recognition.



Tesseract works with Java's Files, but doesn't support MultipartFile, which we get by accepting a file through our form. To mitigate this, we've added a simple convert() method, which converts the MultipartFile into a regular File.</p>

Once we've extracted the text using Tesseract, we simply add it to the model, alongside the scanned image and append it to the redirected view - result.</p>

<h3>Views:</h3>
<p>Now, let's define a view which we can use to simply upload a file via a form:
Running this app will greet us with a plain interface:</p>

![image](https://user-images.githubusercontent.com/101090514/174449520-c9dd3140-1b3c-4411-b5fe-4b82e69e06e6.png)
<p>Adding an image and submitting it will result in the text being extracted and the image shown on the screen And the resulting page:</p>
![image](https://user-images.githubusercontent.com/101090514/174449548-c1b242dd-bd2e-4208-9c77-82e9962a35b8.png)




<h3>Conclusion:</h3?
<p>Using Google's Tesseract engine, we built an extremely simple app that accepts an image through a form, extracts the textual contents from it and returns to us the submitted image.

Optical Character Recognition can come in handy when you wish to digitize content, especially when it comes to documents. These are easy to scan and are fairly accurate when it comes to extracting content. Of course, it's always wise to proofread the resulting document for potential errors.</p>
