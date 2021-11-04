(ns taipei-404.clojure.lesson4)

;; --- Part 4, collections

;; Standard collections:
;; - list, vector, set, hashmap
;; - strings are a collection of characters

;; Collection creation via functions
(list 1 2 3)           ;; (1 2 3)
(vector 1 2 3)         ;; [1 2 3]
(hash-set 1 2 3)       ;; #{1 2 3}
(hash-map :a 10 :b 20) ;; {a: 10 :b 20}












;; Testing the type of collection
list?
vector?
set?
map?
string?

(list? '(1 :a "hello"))          ;; true
(list? [1 :a "hello"])           ;; false
(list? #{1 :a "hello"})          ;; false
(list? {:name "Alice", :age 25}) ;; false
(list? "Alice")                  ;; false

(vector? '(1 :a "hello"))          ;; false
(vector? [1 :a "hello"])           ;; true
(vector? #{1 :a "hello"})          ;; false
(vector? {:name "Alice", :age 25}) ;; false
(vector? "Alice")                  ;; false

(set? '(1 :a "hello"))          ;; false
(set? [1 :a "hello"])           ;; false
(set? #{1 :a "hello"})          ;; true
(set? {:name "Alice", :age 25}) ;; false
(set? "Alice")                  ;; false

(map? '(1 :a "hello"))          ;; false
(map? [1 :a "hello"])           ;; false
(map? #{1 :a "hello"})          ;; false
(map? {:name "Alice", :age 25}) ;; true
(map? "Alice")                  ;; false

(string? '(1 :a "hello"))          ;; false
(string? [1 :a "hello"])           ;; false
(string? #{1 :a "hello"})          ;; false
(string? {:name "Alice", :age 25}) ;; false
(string? "Alice")                  ;; true









;; All standard collections are `seqable?`, i.e. they can be transformed into a sequence.
(seqable? '(1 :a "hello"))          ;; true
(seqable? [1 :a "hello"])           ;; true
(seqable? #{1 :a "hello"})          ;; true
(seqable? {:name "Alice", :age 25}) ;; true
(seqable? "hello")                  ;; true

;; Create a sequence from the collection
(seq '(1 :a "hello"))          ;; (1 :a "hello")
(seq [1 :a "hello"])           ;; (1 :a "hello")
(seq #{1 :a "hello"})          ;; ("hello" 1 :a), the elements are in unreliable, yet reproducible order
(seq {:name "Alice", :age 25}) ;; ([:name "Alice"] [:age 25]), unreliable yet reproducible order
(seq "hello")                  ;; (\h \e \l \l \o)

;; seq on empty collections returns nil
(seq '())  ;; nil
(seq [])   ;; nil
(seq #{})  ;; nil
(seq {})   ;; nil
(seq "")   ;; nil







;; Functions on sequences
first rest next

(first (seq [1 :a "hello"]))      ;; 1
(first [1 :a "hello"])            ;; 1
(first {:name "Alice", :age 25})  ;; [:name "Alice"]
(first [])                        ;; nil
(first nil)                       ;; nil

(rest [1 :a "hello"])            ;; (:a "hello")
(rest {:name "Alice", :age 25})  ;; ([:age 25])
(rest [])                        ;; ()
(rest nil)                       ;; ()

(next [1 :a "hello"]) ;; (:a "hello")   ... truthy
(next [])             ;; nil            ... falsey
(next nil)            ;; nil



;; Example: uses seq
(defn my-empty? [coll]
  (if (seq coll) false true))

(my-empty? [1 :a "hello"]) ;; false
(my-empty? [])             ;; true



;; Example: first, next
(defn my-second [coll]
  (first (next coll)))

(my-second [1 :a "hello"])   ;; :a

(defn my-third [coll]
  (first (next (next coll))))

(my-third [1 :a "hello"])   ;; :"hello"



;; Example:
(defn my-nth [coll index]
  (if (zero? index)
    (first coll)
    (my-nth (rest coll) (dec index))))

(my-nth [1 :a "hello"] 0)   ;; 1
(my-nth [1 :a "hello"] 1)   ;; :a
(my-nth [1 :a "hello"] 2)   ;; "hello"
(my-nth [1 :a "hello"] 3)   ;; nil



;; Example: seq, rest, recursion
(defn my-count [coll]
  ;;(println coll)
  (if (seq coll)
    (inc (my-count (rest coll)))
    0))

(my-count [1 :a "hello"])  ;; 3
(my-count [])              ;; 0



;; More functions on sequences
empty? second nth count reverse last









;; Range
(range 10)       ;; (0 1 2 3 4 5 6 7 8 9)
(range 10 20)    ;; (10 11 12 13 14 15 16 17 18 19)
(range 10 20 3)  ;; (10 13 16 19)



;; Stream functions on sequences
take take-while take-last
drop drop-while drop-last

(defn small? [x] (<= x 5))

(take 3 (range 10))             ;; (0 1 2)
(take-while small? (range 10))  ;; (0 1 2 3 4 5)
(take-last 3 (range 10))        ;; (7 8 9)

(drop 3 (range 10))             ;; (3 4 5 6 7 8 9)
(drop-while small? (range 10))  ;; (6 7 8 9)
(drop-last 3 (range 10))        ;; (0 1 2 3 4 5 6)








;; Construct new data structures.

;; Note: The data structures are immutable,
;; new structures are created each time.

;; Add to front of a list
(cons :a '(:b :c))     ;; (:a :b :c)
(cons :a nil)          ;; (:a)

;; Remove from front of a list
(next '(:a :b :c))     ;; (:b :c)




;; Add some elements to back of a vector
(conj [:a :b] :c)             ;; [:a :b :c]
(conj [:a :b] :c :d)          ;; [:a :b :c :d]

;; Add a collection of elements to the back of a vector
(into [:a :b] [:x :y])     ;; [:a :b :x :y]

;; Change some elements inside a vector
(assoc [:a :b :c] 1 :x)       ;; [:a :x :c]
(assoc [:a :b :c] 1 :x 2 :y)  ;; [:a :x :y]

;; Remove one element from the back of a vector
(pop [:a :b :c])              ;; [:a :b]




;; Add some elements to a set
(conj #{:a :b} :c)        ;; #{:c :b :a}
(conj #{:a :b} :c :d)     ;; #{:c :b :d :a}

;; Add a collection of elements to a set
(into #{:a :b} [:x :y])   ;; #{:y :b :x :a}

;; Remove some elements from a set
(disj #{:a :b :c} :b)     ;; #{:c :a}
(disj #{:a :b :c} :b :a)  ;; #{:c}




;; Add some elements to a hash-map
(assoc {:name "Alice"}
  :age 25)        ;; {:name "Alice", :age 25}

(assoc {:name "Alice"}
  :age 25
  :happy? true)   ;; {:name "Alice", :age 25, :happy? true}


;; Replace some elements inside a hash-map
(assoc {:name "Alice"
        :age 25}
  :age 26)        ;; {:name "Alice", :age 26}


;; Update an element inside a hash-map
(update {:name "Alice"
         :age 25}
        :age
        inc)      ;; {:name "Alice", :age 26}


;; Remove a relation [key value] from a hash-map
(dissoc {:name "Alice"
         :age 25}
        :age)     ;; {:name "Alice"}












;; Testing keys in collections

;; contains? does not work on lists, lists have no key

;; Presence of an index in a vector
(contains? [:a :b] 0)      ;; true
(contains? [:a :b] 1)      ;; true
(contains? [:a :b] 2)      ;; false

;; Presence of elements in a set
(contains? #{:a :b :c} :b) ;; true
(contains? #{:a :b :c} :d) ;; false

;; Presence of key in a hash-map
(contains? {:a "hi", :b "hello"} :a) ;; true
(contains? {:a "hi", :b "hello"} :c) ;; false










;; Reading elements in collections (sequential access)

;; Sequential access to the element at index 2 in a list
(nth '(:a :b :c :d :e) 2)   ;; :c  (slow)

;; Sequential access to the last element in a collection
(last '(:a :b :c :d :e))    ;; :e  (slow)
(last [:a :b :c :d :e])     ;; :e  (slow)




;; Reading elements in collections (immediate access)

;; Immediate access to the element at index 2 in a vector
(nth [:a :b :c :d :e] 2)   ;; :c
(nth [:a :b :c :d :e] 10)  ;; Execution error (IndexOutOfBoundsException)
(get [:a :b :c :d :e] 2)   ;; :c
(get [:a :b :c :d :e] 10)  ;; nil

;; Immediate access to the value associated to a key in a hash-map
(get {:name "Alice", :age 5} :name)   ;; "Alice"
(get {:name "Alice", :age 5} :hobby)  ;; nil

;; Immediate access to a value in a set
(get #{:a :b :c} :b)         ;; :b
(contains? #{:a :b :c} :b)   ;; true  (more readable)




;; Vectors are also functions
([:a :b :c :d :e] 2)       ;; :c
([:a :b :c :d :e] 10)      ;; Execution error (IndexOutOfBoundsException)

;; Sets are also functions
(#{:a :b :c} :b)           ;; :b
(#{:a :b :c} :d)           ;; nil

;; Hash-maps are also functions
({:name "Alice", :age 5} :name)   ;; "Alice"
({:name "Alice", :age 5} :hobby)  ;; nil








;; Updating nested collections

(assoc-in [{:name "Alice", :age 25}
           {:name "Bob", :age 27}
           {:name "Charlie", :age 32}]
          [2 :age]
          20)
;; [{:name "Alice", :age 25}
;;  {:name "Bob", :age 27}
;;  {:name "Charlie", :age 20}]

(update-in [{:name "Alice", :age 25}
            {:name "Bob", :age 27}
            {:name "Charlie", :age 32}]
           [2 :age]
           inc)
;; [{:name "Alice", :age 25}
;;  {:name "Bob", :age 27}
;;  {:name "Charlie", :age 33}]

(update-in [{:name "Alice", :age 25}
            {:name "Bob", :age 27}
            {:name "Charlie", :age 32}]
           [2 :age]
           + 10 20)
;; [{:name "Alice", :age 25}
;;  {:name "Bob", :age 27}
;;  {:name "Charlie", :age 62}]

(update [{:name "Alice", :age 25}
         {:name "Bob", :age 27}
         {:name "Charlie", :age 32}]
        2
        dissoc :age)
;; [{:name "Alice", :age 25}
;;  {:name "Bob", :age 27}
;;  {:name "Charlie"}]
