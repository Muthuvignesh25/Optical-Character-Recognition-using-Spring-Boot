,h3>Introduction:<h3/>
                Developing symbols which have some value is a trait unique to humans. Recognizing these symbols and understanding the letters on an image is absolutely normal for us. We never really grasp letters like computers do, we completely base our ability to read them on our sight.

On the other hand, computers need something more concrete and organized to work with. They need a digital representation, not a graphical one.

Sometimes, this simply isn't possible. Sometimes, we wish to automate a task of rewriting text from an image with our own hands.

For these tasks, Optical Character Recognition (OCR) was devised as a way to allow computers to "read" graphical content as text, similar to how humans do. Of course these systems, while relatively accurate, can still be off quite a bit. Even if they are, fixing up the mistakes of the system is still a lot easier and faster than doing everything from scratch by hand.

Like all systems, similar-in-nature, optical character recognition software trains on prepared datasets that feed it enough data to learn the difference between characters. It's also very important how these networks learn, if we want to make them accurate, though this is a topic for another article.


Instead of reinventing the wheel and coming up with a very complex (but useful) solution, let's settle down a bit and use what's already offered.

Tesseract
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


Feeding it such a picture will produce a perfect result:

Optical Character Recognition in Java is made easy with the help of Tesseract'
However, this image is extremely easy to scan. It's normalized, high in resolution and the font is consistent.

Let's see what happens if I try to write something down myself, on a piece of paper, and we let it pass through the app:


Free eBook: Git Essentials
Check out our hands-on, practical guide to learning Git, with best-practices, industry-accepted standards, and included cheat sheet. Stop Googling Git commands and actually learn it!


Download the eBook  

We can instantly see the difference it makes:

A411“, written texz: is different {mm compatar generated but
Some words are perfectly fine and you could easily make out "written text is different from computer generated", but the first and last words are off by a lot.

Now, to make this a bit easier to use, let's transfer it into a very simple Spring Boot application to serve up the result in a more graphically pleasing way.

Implementation
Spring Boot Application
First off, let's start by generating our project through Spring Initializr. Include the spring-boot-starter-web and spring-boot-starter-thymeleaf dependencies. We'll import Tesseract manually:


Controller
The app doesn't need more than a single controller, which serves up our two views and handles the picture upload and optical character recognition:



Tesseract works with Java's Files, but doesn't support MultipartFile, which we get by accepting a file through our form. To mitigate this, we've added a simple convert() method, which converts the MultipartFile into a regular File.

Once we've extracted the text using Tesseract, we simply add it to the model, alongside the scanned image and append it to the redirected view - result.

Views
Now, let's define a view which we can use to simply upload a file via a form:

<html>
<body>
<h1>Upload a file for OCR:</h1>

<form method="POST" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file" /><br/><br/>
    <input type="submit" value="Submit" />
</form>

</body>
</html>
And the resulting page:

<html xmlns:th="http://www.thymeleaf.org">
<body>

<h1>Extracted Content:</h1>
<h2>><span th:text="${text}"></span></h2>

<p>From the image:</p>
<img th:src="'/' + ${file.getOriginalFilename()}"/>
</body>
</html>
Running this app will greet us with a plain interface:



Adding an image and submitting it will result in the text being extracted and the image shown on the screen:


Success!

Conclusion
Using Google's Tesseract engine, we built an extremely simple app that accepts an image through a form, extracts the textual contents from it and returns to us the submitted image.

This isn't a particularly useful application in and of itself, due to the limited power of Tesseract and the fact that the app is too simple for any other use besides demonstration purposes, but it should serve as a fun tool you could implement and test with.

Optical Character Recognition can come in handy when you wish to digitize content, especially when it comes to documents. These are easy to scan and are fairly accurate when it comes to extracting content. Of course, it's always wise to proofread the resulting document for potential errors.
