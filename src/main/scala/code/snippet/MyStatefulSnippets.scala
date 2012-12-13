package code.snippet

import net.liftweb._
import http._
import common._
import util.Helpers._
import scala.xml.NodeSeq

import code.model._

class MyArticleStateful extends StatefulSnippet {
  
  private var title = ""
  private var content = ""
  
  // StatefulSnippet requires an explicit dispatch
  // to the method.
  def dispatch = {case _ => render}  
  
  def render = 
    "name=title" #> SHtml.text(title, title = _) &
    "name=content" #> SHtml.textarea(content, content = _) &
    "type=submit" #> SHtml.onSubmitUnit(save)
    
  private def save() = {
    println("save -> title=" + title + ", content=" + content)
    if (title.isEmpty || content.isEmpty) S.error("both", "Both fields have to be filled.")
    Articles.add(Article(Articles.generateId, title, content))
  }
  
}

class MyTableStateful extends StatefulSnippet {
  
  // StatefulSnippet requires an explicit dispatch
  // to the method.
  def dispatch = {case _ => render}
  
  def render =
    "* *" #> Articles.articles.map{article =>
      "@id [value]" #> article.id &
      "@title [value]" #> article.title &
      "@content [value]" #> article.content
    }     
  
}