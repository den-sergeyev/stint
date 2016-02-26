class Node
  attr_accessor :key, :data, :parent, :left, :right

  def initalize(key, data = [], parent: nil, left: nil, right: nil)
    raise NoKeyException.new unless key

    @key = key
    @data = data
    @parent = parent
    @left = left
    @right = right
  end

  def leaf?
    !left && !right
  end

  class NoKeyException < StandardError; end
end
