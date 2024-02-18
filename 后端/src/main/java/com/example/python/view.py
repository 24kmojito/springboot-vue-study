# import xlrd
# import sys
# import numpy as np
# from pyecharts.charts import Bar, Line
# from pyecharts.charts import Pie, Grid
# from pyecharts import options as opts
#
# # ==================== 准备数据 ====================
# # 导入Excel 文件
#
# data = xlrd.open_workbook(sys.argv[1])
# print(1)
# # 载入第一个表格
# table = data.sheets()[0]
#
# tables = []
#
#
# def Read_Excel(excel):
#     # 从第2行开始读取数据，因为这个Excel文件里面从第四行开始才是考生信息
#     for rows in range(1, excel.nrows - 1):
#         dict_ = {"name": "", "score": "", "star": ""}
#         dict_["name"] = table.cell_value(rows, 2)
#         dict_["score"] = table.cell_value(rows, 10)
#         dict_["star"] = table.cell_value(rows, 11)
#         # dict_["remarks"] = table.cell_value(rows, 3)
#         # dict_["preliminary_score"] = table.cell_value(rows, 5)
#         # dict_["retest_score"] = table.cell_value(rows, 6)
#         # dict_["total_score"] = table.cell_value(rows, 7)
#         # dict_["ranking"] = table.cell_value(rows, 10)
#         # 将未被录取或者非普通计划录取的考生滤除
#         # if dict_["status"] == str("是") and dict_["remarks"] == str("全日制"):
#         if dict_["score"] != "":
#             tables.append(dict_)
#         # tables.append(dict_)
#
# Read_Excel(table)
# for i in tables:
#     print(i)
#
# num_score_1_3 = 0
# num_score_3_6 = 0
# num_score_6_8 = 0
# num_score_8_10 = 0
#
# min_score = 999
# max_score = 0
#
# # 将各个分段的数量统计
# for i in tables:
#     temp = i["score"]
#     score = float(temp)
#     if score > float(max_score):
#         max_score = score
#     if score < float(min_score):
#         min_score = score
#
#     if (score >= 1.0) & (score < 3.0):
#         num_score_1_3 = num_score_1_3 + 1
#     elif (score >= 3.0) & (score < 6.0):
#         num_score_3_6 = num_score_3_6 + 1
#     elif (score >= 6.0) & (score < 8.0):
#         num_score_6_8 = num_score_6_8 + 1
#     elif (score >= 8.0) & (score < 10.0):
#         num_score_8_10 = num_score_8_10 + 1
#
#
# # 构建两个元组用以后期建表方便
# bar_x_axis_data = ("1-3", "3-6", "6-8", "8-10")
# bar_y_axis_data = (num_score_1_3, num_score_3_6, num_score_6_8,num_score_8_10)
#
# print(num_score_1_3, num_score_3_6, num_score_6_8,num_score_8_10)
#
# # c = (
# #     Bar()
# #         .add_xaxis(bar_x_axis_data)
# #         .add_yaxis("电影得分", bar_y_axis_data, color="#af00ff")
# #         .set_global_opts(title_opts=opts.TitleOpts(title="数量"))
# #         .render("./录取数据图.html")
# # )
#
# bar = (
#     Bar()
#         .add_xaxis(bar_x_axis_data)
#         .add_yaxis("得分", bar_y_axis_data)
#         # .add_yaxis("商家B", [57, 134, 137, 129, 145, 60, 49])
#         .set_global_opts(title_opts=opts.TitleOpts(title="电影得分"))
# )
# a = bar.render("./电影得分图.html")
#
# print(a)