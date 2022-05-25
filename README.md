# Tweet-cleaner-and-generator
A Tweet cleaner that receives raw Twitter tweet data as input and returns that input with particular elements removed.

The elements removed are as follows

@names, e.g. @donaldjtrump, and any others that are mentioned.
URLs.
hashtags, e.g. #Hillary, etc..
The strings “RT” or ‘rt (RT means ‘retweet’).
Any input element that contains digits.
Ellipsis (i.e. three/four dots: ...). These appear in the text where a post is split into multiple tweets.
Just remove the ellipsis.
All punctuation marks BUT see the exceptions below.
2
Remove hyphens from hyphenated words. This will render some words ‘wrong’ but don’t worry about
that.

Expentions to the removal of punctuation

Do not remove exclamation marks that are at the end of a word. The word “great!” is to be treated
as a single word and is distinct from the word “great”. You don’t need to consider there being more
than one exclamation mark at the end of a word.
Do not remove question marks that are at the end of a word. The word “really?” is to be treated as
a single word and is distinct from the word “really”. You don’t need to consider there being more
than one question mark at the end of a word.
Do not remove possessive inverted commas, e.g. in the words“Biden’s”, “America’s”, “James’ ”.

The cleaner is used to generate Tweets in the style of Donald Trump
