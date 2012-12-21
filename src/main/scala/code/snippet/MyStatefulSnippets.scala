package code.snippet

import net.liftweb._
import http._
import js.{JsCmds, JsCmd}
import JsCmds._
import util.CssSel
import util.Helpers._

import code.model._

class MyArticleStateful extends StatefulSnippet {

  // StatefulSnippet requires an explicit dispatch
  // to the method.
  def dispatch = {case _ => render}  
  
  def render = {
		var title = ""
		var content = ""

    "@title" #> SHtml.text(title, title = _) &
    "@content" #> SHtml.textarea(content, content = _) &
    "type=submit" #> SHtml.onSubmitUnit(() => save(title, content))
	}

  private def save(title: String, content: String) {
    println("save -> title=" + title + ", content=" + content)
    if (title.isEmpty || content.isEmpty) S.error("both", "Both fields have to be filled.")
    Articles.add(Article(Articles.generateId, title, content))
  }
  
}

class MyTableStateful extends StatefulSnippet {

	// StatefulSnippet requires an explicit dispatch
	// to the method.
	def dispatch = {case _ => render}

	def render = "* *" #> Articles.getArticlesList.map{renderArticle(_)}

	/**
	 * Function should render a table row for given article. Also it will bind the article and the appropriate input
	 * fields
	 * @param article has to be rendered
	 */
	private def renderArticle(article: Article) = {
		var inputTitle = article.title
		var inputContent = article.content

		"@id [value]" #> article.id &
			"@title" #>  SHtml.text(inputTitle, inputTitle = _) &
			"@content" #> SHtml.text(inputContent, inputContent = _) &
			"@edit-button" #> SHtml.onSubmitUnit(() => edit(article, inputTitle, inputContent)) &
			"@remove-button" #> SHtml.onSubmitUnit(() => remove(article))
	}

	private def edit(article: Article, inputTitle: String, inputContent: String) {
		println("--> edit article=" + article + ", inputTitle=" + inputTitle + ", inputContent=" + inputContent)
// FIXME error: reassignment to val
//		article.title = inputTitle
//		article.content = inputContent
	}

	private def remove(article: Article) {
		println("--> remove article=" + article)
		Articles.remove(article)
	}

}