package data.store

import data.entity.Order
import java.util

trait OrderStore {

  def find(orderId: String): Order
  def findByUserId(userId: String): util.List[Order]
  def findCompleted(): util.List[Order]
  def findAll(): util.List[Order]
  def update(order: Order): Order
  def save(order: Order): Order
  def delete(orderId: String): Boolean
}
