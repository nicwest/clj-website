(ns clj-website.templates.layout
  (:use [hiccup.core :only (html)]
        [hiccup.page :only (html5 include-css include-js)]))

(defn fa-icon
  [icon-name]
  (html 
    [:i {:class (str "fa fa-" icon-name)}]))

(defn tooltip-link
  ([href title options & content]
   (let [placement (:placement options)
         target (:target options)
         html-data (:html options)]
     (html
       [:a {:href href
            :title title
            :rel "tooltip"
            :data-toggle "tooltip"
            :data-placement placement
            :data-html (if (nil? html-data) nil "true")
            :target target}
        content]))))

(def footer
  (html 
    [:div {:class "container"}
     [:div {:class "float-left"}
      (tooltip-link "/" "home" {:placement "top"} "Nic West")]
     [:div {:class "float-right"}
     (tooltip-link "#" "London" {:placement "top"} (fa-icon "map-marker"))
     (tooltip-link "#" "python<br />vimscript<br />(clojure)" {:placement "top" :html true} (fa-icon "code"))
     (tooltip-link "http://weareleto.com" "junior web developer" {:placement "top" :target "_blank"} (fa-icon "briefcase"))
     "|&nbsp;&nbsp;"
     (tooltip-link "/feed.atom" "feeds" {:placement "top"} (fa-icon "rss"))
     (tooltip-link "https://twitter.com/west_nic" "@west_nic" {:placement "top" :target "_blank"} (fa-icon "twitter"))
     (tooltip-link "https://github.com/nicwest" "github" {:placement "top" :target "_blank"} (fa-icon "github"))]
     [:div {:class "clear-both"}]]))

(defn content
  [& body]
  (html [:div {:class "container"} body])) 

(defn common 
  ([title assets & body]
   (let [css (:css assets)
         js (:js assets)]
     (html5
       [:head 
        [:meta {:charset "utf-8"}]
        [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge,chrome=1"}]
        [:meta {:name "viewport" :content "width=device-width, initial-scale=1, maximum-scale=1"}]
        [:title (if (nil? title) "Nic West" (str "Nic West - " title))]
        (include-css "/css/bootstrap.min.css"
                     "/css/font-awesome.min.css"
                     "/css/font-mfizz.css"
                     "/css/hybrid.css"
                     "/css/base.css")
        (apply include-css css)]
       "<!--[if lt IE 9]>"
       (include-js "https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"
                   "https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js")
       "<![endif]-->"
       (include-js "//use.typekit.net/juc4hvt.js")
       [:script {:type "text/javascript"} "try{Typekit.load();}catch(e){}"]
       [:body
        [:div {:class "content"} (content body)]
        [:div {:class "footer"} footer]
        (include-js "js/jquery.min.js"
                    "js/bootstrap.min.js"
                    "js/bootstrap.enables.js"
                    "js/highlight.pack.js")
        [:script {:type "text/javascript"} "hljs.initHighlightingOnLoad();"]
        (apply include-js js)])))
  ([title body]
   (common title {} body))
  ([body]
   (common nil {} body)))
