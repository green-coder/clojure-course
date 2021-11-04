(ns taipei-404.clojure.lesson2)

(def hello-world 100)

;; --- Part 2, values

;; In Clojure, we manipulate values most of the time.
;; Values are immutable, they never change.

;; Clojure has those types of values:

;; Absence of value. Nothing.
nil

;; Booleans
false
true

;; Numbers
12
12.3

;; Characters
\a \b \c \A \B \C \0 \1 \2 \space \newline

;; String
"this is a text"
"this is a text
on multiple lines"
"this is a text\non multiple lines"
(str "this is a text" \newline "on multiple lines")

;; Keywords
:hello-world

;; Symbols
hello-world
'hello-world
(quote hello-world)

;; Functions
(fn [x] (+ x 1))

;; Collections

;; List: a sequence of values, read access is sequential
'()
'(nil false true 12 12.3 \a "hi" :hello-world hello-world (fn [x] (+ x 1)) ())

;; Vector: a sequence of values, read access using an index is immediate
[] ;; brackets
[nil false true 12 12.3 \a "hi" :hello-world hello-world (fn [x] (+ x 1)) () []]

;; Set: a set of values
#{} ;; sharp + curly brackets
#{nil false true 12 12.3 \a "hi" :hello-world hello-world (fn [x] (+ x 1)) [] #{}}

;; Hashmap: an associative data structure
{}
{nil              "the nil value"
 false            "the false value"
 true             "the true value"
 12               "a number"
 12.3             "a decimal number"
 \a               "a character"
 "hello-world"    "a string"
 :hello-world     "a keyword"
 hello-world      "a symbol"
 (fn [x] (+ x 1)) "a function"
 ;;()               "a list"
 []               "a vector"
 #{}              "a set"
 {}               "a hashmap"}

;; The collection above are all collection literals.
;; The evaluation of a collection literal is the collection of the evaluation of all their elements.

;; Those expressions evaluate to the same value:
{12 "a number"}
{(* 3 4) (str "a " "number")}
