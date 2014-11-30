Clojure, and Blog Re-Write
==========================

Over the last few months I have been dipping into a new language: 
[Clojure](http://clojure.org/). Clojure is the first lispy language I have
worked with and aside from a few misspent weekends with the Android SDK, the
closest I have come to the JVM.

```clojure
(map println (:todo (reduce
  (fn [result text] 
    (let [todo (:todo result)
          step (:step result)
          step-inc (inc step)
          line (str "step " step ". " text)]
      {:todo (concat todo [line]) :step step-inc}))
  {:todo [] :step 1}
  ["learn clojure" "write terrible blog/post" "???" "PROFIT!"])))
```

This is a bad example of Clojure. (There are simpler ways of doing this)

I dropped my horrible [python blog](https://github.com/nicwest/md-website), and
replaced it with what is probably a slightly worse
[clojure blog](https://github.com/nicwest/clj-website). Exciting!

[Ring](https://github.com/ring-clojure/ring) +
[Compojure](https://github.com/weavejester/compojure) makes a very lightweight
web-framework which feels really well thought out.
[Hiccup](https://github.com/weavejester/hiccup) is now my preferred way of
writing html. Experimenting with 
[ClojureScript](https://github.com/clojure/clojurescript) (Javascript written in
clojure), was pretty awesome but I'm not really a JS person so I'm sure a lot of
the really cool things escaped me.
[Om](https://github.com/swannodette/om) (Clojure + ClojureScript + ReactJS)
was more or less incomprehensible, but with some react experience it looks like
a lot of fun!
