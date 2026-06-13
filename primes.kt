/**
 * 入力した数以下の素数を関数型プログラミングで表示するKotlin実装
 */

fun main() {
    print("素数を求める上限の数を入力してください: ")
    val n = readLine()?.toIntOrNull() ?: 2
    
    printPrimesUpTo(n)
}

/**
 * 指定された数以下の素数をすべて表示する
 * @param limit 上限の数
 */
fun printPrimesUpTo(limit: Int) {
    (2..limit)
        .filter { isPrime(it) }
        .forEach { println(it) }
}

/**
 * 素数判定を関数型で実装
 * @param num 判定対象の数
 * @return numが素数の場合true
 */
fun isPrime(num: Int): Boolean {
    if (num < 2) return false
    if (num == 2) return true
    if (num % 2 == 0) return false
    
    return (3..num / 2 step 2)
        .none { num % it == 0 }
}

/**
 * 素数のシーケンスを生成する遅延評価版
 * @param limit 上限の数
 * @return 素数のシーケンス
 */
fun primesSequence(limit: Int): Sequence<Int> {
    return (2..limit).asSequence()
        .filter { isPrime(it) }
}

/**
 * 使用例：複数の処理パイプラインの例
 */
fun main2() {
    val limit = 100
    
    // 例1: 素数を表示
    println("=== 100以下の素数 ===")
    primesSequence(limit).forEach { println(it) }
    
    // 例2: 素数の個数を取得
    println("\n=== 素数の個数 ===")
    val count = primesSequence(limit).count()
    println("100以下の素数は $count 個です")
    
    // 例3: 素数の合計を計算
    println("\n=== 素数の合計 ===")
    val sum = primesSequence(limit).sum()
    println("100以下の素数の合計は $sum です")
    
    // 例4: 最初の10個の素数を取得
    println("\n=== 最初の10個の素数 ===")
    primesSequence(limit)
        .take(10)
        .forEach { println(it) }
    
    // 例5: 特定の条件でフィルタリング（50以上100以下の素数）
    println("\n=== 50以上100以下の素数 ===")
    primesSequence(limit)
        .filter { it >= 50 }
        .forEach { println(it) }
}
