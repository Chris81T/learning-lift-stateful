/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.model

case class Article(id: Long, title: String, content: String)

object Articles {

  var articles = List(Article(0, "Peter Parker", "Is spiderman."),
											Article(1, "Bruce Wayne", "Is the batman! The only one dark knight."),
											Article(2, "Clark Kent", "Is superman. The strongest man of the world."))
  
  /**
   * Note that list-operation :+ has a complexity of O(n). If you need this operation frequently, 
   * or for long lists, consider using another data type (e.g. a ListBuffer).
   */
  def add(article: Article) {
    // TODO actually this method does not work ! Fix it...
		println("Incoming article=" + article)
    articles ::: List(article)
    println("following articles exist's=" + articles)
  }

  def generateId() : Long = articles.length
  
}
