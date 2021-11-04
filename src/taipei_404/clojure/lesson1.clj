;; Clojure development: Interactive development

;; REPL - Read Evaluate Print Loop

;; Send expressions to the REPL using a keyboard shortcut





















*ns*

(ns taipei-404.clojure.lesson1)

;; --- Part 1, introduction

; This is a comment
;; This is also a comment

#_ "this is a commented Clojure expression"
#_ (println "this is a commented Clojure expression")







;; Numbers
12
12.3

;; Some functions in standard library `clojure.core`
(inc 12)
(+ 12 12.3)

;; Function with side effect
(println "Hello")










;; Global definition - side effect
(def a 12)
(def b 12.3)
(+ a b)

;; Local definition - no side effect
(let [a 12 b 12.3] (+ a b))

;; The character "," is interpreted as a space
(let [a 12, b 12.3] (+ a b))

;; Let can have multiple bodies. It returns the value of the last one.
(let [a 12
      b 12.3]
  (println "Hello")
  (+ a b))









;; We can write our own function
(fn [x] (+ x 1))
((fn [x] (+ x 1)) 12)

(def my-inc (fn [x] (+ x 1)))
(my-inc 12)

(defn my-inc [x] (+ x 1))

;; New lines helps make things easier to read.
(defn my-inc [x]
  (+ x 1))

;; Fn can have multiples body. It returns the value of the last one.
(defn my-inc [x]
  (println "I am adding 1")
  (+ x 1))

;; Functions can have multiple arities, like the `+` function
(defn f
  ([] "zero")
  ([x] "one")
  ([x y] "two")
  ([x y & more] "more than two"))

(f)
(f 1)
(f 1 2)
(f 1 2 3)
(f 1 2 3 4)
