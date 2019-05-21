package data.repository.impl

import java.util

import data.entity.Order
import data.repository.OrderRepository
import data.store.OrderStore
import javax.inject.Inject

class SimpleOrderRepository @Inject()(private val store: OrderStore)
  extends OrderRepository{
  override def findByUserId(userId: String): util.List[Order] = {
    store.findByUserId(userId)
  }

  override def save(obj: Order): Order = {
    val order = store.find(obj.getId)
    if(order != null){
      throw new IllegalArgumentException("Object already exists")
    }else{
      store.save(obj)
    }
  }

  override def getAll: util.List[Order] = {
    store.findAll()
  }

  override def delete(id: String): Boolean = {
    val order = store.find(id)
    if(order == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      store.delete(id)
    }
  }

  override def update(obj: Order): Order = {
    val order = store.find(obj.getId)
    if(order == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      store.update(obj)
    }
  }

  override def findBy(id: String): Order = {
    val order = store.find(id)
    if(order == null){
      throw new NoSuchElementException("There is no such object")
    }else{
      order
    }
  }

  override def findCompleted(): util.List[Order] = {
    store.findCompleted()
  }
}
