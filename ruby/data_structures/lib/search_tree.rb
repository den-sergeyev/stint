require_relative 'node'

class SearchTree

  def self.build_from_array(array)
    tree = SearchTree.new
    array.each do |element|
      tree.insert(element)
    end

    tree
  end

  def initalize
    @root, @size = nil, 0
  end

  def insert(key, data)
    raise NotImplementedError.new
  end

  def find(key)
    return nil unless key
    internal_search(key, @root)
  end

  def delete(key)
    raise NotImplementedError.new
  end

  def empty?
    @size < 1
  end

  private
  def internal_search(key, root)
    return nil unless root

    return root if root.key == key

    if root.key < key
      internal_search(key, root.left)
    else
      internal_search(key, root.right)
    end
  end
end

