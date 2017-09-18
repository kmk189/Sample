#include <linux/fs.h>
#include <linux/init.h>
#include <linux/miscdevice.h>
#include <linux/module.h>
#include <asm/uaccess.h>

#include "pi.h" //provided pi generator

/*
 * pi_read is the function for when programs read from /dev/pi 
 */

static ssize_t pi_read(struct file * file, char * buf, size_t count, loff_t *ppos){
	unsigned int quantity; //for space to make
	char *buffer; //char pointer for the kmalloc
	quantity = (4 - (*ppos + count) % 4) + *ppos + count; //rounding up by 4

	buffer = (char*)kmalloc(quantity, GFP_KERNEL); //space allocation in multiples of 4

	pi(buffer, quantity); //send the space allocated and rounded value to get the numbers of pi

	//copy to the user space and free the kmalloc'ed space
	if(copy_to_user(buf, buffer + *ppos, count)){
		kfree(buffer);
		return -EINVAL;
	}

	kfree(buffer); //free the kmalloc
	*ppos += count; //incrementing position
	return count; //returning the count
}

/*
 * The only file operation we care about is read.
 */

static const struct file_operations pi_fops = {
	.owner		= THIS_MODULE,
	.read		= pi_read,
};

static struct miscdevice pi_driver = {
	/*
	 * We don't care what minor number we end up with, so tell the
	 * kernel to just pick one.
	 */
	MISC_DYNAMIC_MINOR,
	/*
	 * Name ourselves /dev/pi_driver.
	 */
	"pi_driver",
	/*
	 * What functions to call when a program performs file
	 * operations on the device.
	 */
	&pi_fops
};

static int __init pi_init(void){
	int ret;
	ret = misc_register(&pi_driver);
	if (ret){
		printk(KERN_ERR "Unable to register pi device\n");
	}
	return ret;
}

module_init(pi_init);

static void __exit pi_exit(void){
	misc_deregister(&pi_driver);
}

module_exit(pi_exit);

MODULE_LICENSE("Dual BSD/GPL");
MODULE_AUTHOR("Kevin Krause <kmk189@pitt.edu>");
MODULE_DESCRIPTION("Module that has the digits of pi");
MODULE_VERSION("dev");
