package org.example.practice10;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Practice10Application {

	@Bean(name = "mergeSortBean")
	public SortingAlgorithm mergeSort() {
		return new MergeSort();
	}

	@Bean(name = "insertionSortBean")
	public SortingAlgorithm insertionSort() {
		return new InsertionSort();
	}

	@Bean(name = "quickSortBean")
	public SortingAlgorithm quickSort() {
		return new QuickSort();
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java -jar <jar_name> <bean_name>");
			return;
		}

		String beanName = args[0];

		ApplicationContext context = new AnnotationConfigApplicationContext(Practice10Application.class);
		SortingAlgorithm sortingAlgorithm = (SortingAlgorithm) context.getBean(beanName);

		sortingAlgorithm.doSort();
	}
}