(ns taipei-404.clojure.lesson3)

;; --- Part 3, branching, truthiness and boolean functions

;; (if <test> <then> <else>)

;; falsey values: nil and false
(if nil   "then" "else") ;; "else"
(if false "then" "else") ;; "else"

;; Truthy values: Literally anything else
(if true   "then" "else") ;; "then"
(if 0      "then" "else") ;; "then"
(if ""     "then" "else") ;; "then"
(if {}     "then" "else") ;; "then"
(if []     "then" "else") ;; "then"
(if \space "then" "else") ;; "then"
(if :xxx   "then" "else") ;; "then"
(if inc    "then" "else") ;; "then"

;; (if <test> <then>) is the same as (if <test> <then> nil)









;; (when <test> <then>) is the same as (if <test> <then> nil)
(when false "then") ;; nil
(when true "then")  ;; "then"

(when true
  (println "hello")
  (+ 2 2))

(when false
  (println "hello")
  (+ 2 2))










;; (cond
;;   <test1> <expr1>
;;   <test2> <expr2>
;;   ,,,)
;;
;; Same as:
;; (if <test1>
;;    <expr1>
;;    (if <test2>
;;       <expr2>
;;       ,,,))
(let [x 5]
  (cond
     (< x 10) "small"
     (< x 100) "big"
     :else "very big"))








;; (case <expr0>
;;   <val1> <expr1>
;;   <val2> <expr2>
;;   ,,,    ,,,
;;   <expr-else>)
;;
;; Same as:
;; (if (= <expr0> <val1>)
;;   <expr1>
;;   (if (= <expr0> <val2>)
;;     <expr2>
;;     (if (= <expr0> ,,,)
;;       ,,,
;;       <expr-else>)))
(let [x 5]
  (case x
     1 "x is one"
     2 "x is two"
     3 "x is three"
     "x is something else"))








;; not
(not nil) ;; true
(not 12)  ;; false

;; if-not - same as (if (not <test>) <then> <else>)
(if-not true "then" "else") ;; "else"
(if-not false "then" "else") ;; "then"

;; when-not - same as (when (not <test>) <then>)
(when-not true "then")  ;; nil
(when-not false "then") ;; "then"











;; Test if 2 values are equal
(= 12 12)       ;; true
(= 12 true)     ;; false
(= nil false)   ;; false
(= [1 2] [1 2]) ;; true
(= '(1 2) [1 2]) ;; true

;; Each created function is unique
(= (fn [x] (+ x 1))
   (fn [x] (+ x 1)))      ;; false

(let [f (fn [x] (+ x 1))]
  (= f f))                ;; true

;; Test if 2 values are not equal - same as (not (= x y))
(not= 12 12)       ;; false
(not= 12 [:a :b])  ;; true











;; Useful predicates:
= not=           ;; works with n arguments
< > <= >=        ;; works with n arguments
nil? some?       ;; same as (= x nil) and (not= x nil)
boolean          ;; false when falsey, true when truthy
boolean?         ;; tests if the value is a boolean
false? true?     ;; same as (= x false) and (= x true)
zero? pos? neg?  ;; same as (= x 0), (> x 0), (< x 0)
odd? even?












;; or - returns the first truthy value, returns the last value if no truthy value was found before
(or false "hi")        ;; "hi"
(or 12 "hi")           ;; 12
(or false nil 12 :foo) ;; 12

;; and - returns the first falsey value, returns the last value if no falsey value was found before
(and false "hi")       ;; false
(and nil "hi")         ;; nil
(and 'a :b nil "hi")   ;; nil

;; every
(every? even? [2 4 6])   ;; true
(every? even? [2 4 5 6]) ;; false
(every? even? [])        ;; true
