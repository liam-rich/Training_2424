import logging
# import logging.handlers
# import os

# Basic logging vs print

print(" [print] Application started") # no level, no timestamp
print(" [print] something went wrong" ) # no way to filter

print("\n Now with logging:")
logging.basicConfig(
    level = logging.DEBUG,
    format = "%(asctime)s | %(levelname)-8s | %(message)s",
    datefmt = "%H:%M:%S",
    force=True #Reset any previos config

)
logging.debug("Detailed debug information")
logging.warning("this is a warning")
logging.info("info message")
logging.error("error message")
logging.critical("Critical Message")

# Named loggers with handlers

# Create a Named Logger
logger = logging.getLogger("test_framework")
logger.setLevel(logging.DEBUG)
logger.handlers.clear() #Clean slate for demo

#Console handler - INFO and above
console = logging.StreamHandler()
console.setLevel(logging.INFO)
console.setFormatter(logging.Formatter(
    " %(levelname)-8s |%(message)s"
))

#File Handler
